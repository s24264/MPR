@Service

public class PersonService {
    private final PersonRepository personRepository;
    private final SubscriptionRepository subsRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, SubscriptionRepository subsRepository) {
        this.personRepository = personRepository;
        this.subsRepository = subsRepository;
    }

    public List<PersonDto> getAll() {
        List<Person> people = personRepository.findAll();
        return mapPersonListToDtoList(people);
    }

    public PersonDto getById(int id) {
        Person person = personRepository.findById(id).orElse(null);
        return mapPersonToDto(person);
    }

    public void save(PersonDto personDto) {
        Person person = mapDtoToPerson(personDto);
        personRepository.save(person);
    }

    public void update(int personId, PersonDto personDto) {
        Person existingPerson = personRepository.findById(personId).orElse(null);
        if (existingPerson != null) {
            Person updatedPerson = mapDtoToPerson(personDto);
            updatedPerson.setId(personId);
            personRepository.save(updatedPerson);
        }
    }

    public void delete(int personId) {
        personRepository.deleteById(personId);
    }

    public void addSubscription(int personId, SubscriptionDto subscriptionDto) {
        Person person = personRepository.findById(personId).orElse(null);
        if (person != null) {
            Subscription subscription = mapDtoToSubscription(subscriptionDto);
            subscription.setPerson(person);
            subsRepository.save(subscription);
        }
    }

    public List<SubscriptionDto> getSubscriptions(int personId) {
        Person person = personRepository.findById(personId).orElse(null);
        if (person != null) {
            List<Subscription> subscriptions = subsRepository.findByPerson(person);
            return mapSubscriptionListToDtoList(subscriptions);
        }
        return null;
    }

    // Helper methods for mapping
    private PersonDto mapPersonToDto(Person person) {
        if (person != null) {
            PersonDto personDto = new PersonDto();
            personDto.setFirstName(person.getFirstName());
            personDto.setLastName(person.getLastName());
            return personDto;
        }
        return null;
    }

    private List<PersonDto> mapPersonListToDtoList(List<Person> people) {
        return people.stream()
                .map(this::mapPersonToDto)
                .collect(Collectors.toList());
    }

    private Person mapDtoToPerson(PersonDto personDto) {
        if (personDto != null) {
            Person person = new Person();
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            return person;
        }
        return null;
    }

    private SubscriptionDto mapSubscriptionToDto(Subscription subscription) {
        if (subscription != null) {
            SubscriptionDto subscriptionDto = new SubscriptionDto();
            subscriptionDto.setPlan(subscription.getPlan());
            subscriptionDto.setStatus(subscription.getStatus());
            subscriptionDto.setPaymentMethod(subscription.getPaymentMethod());
            return subscriptionDto;
        }
        return null;
    }

    private List<SubscriptionDto> mapSubscriptionListToDtoList(List<Subscription> subscriptions) {
        return subscriptions.stream()
                .map(this::mapSubscriptionToDto)
                .collect(Collectors.toList());
    }

    private Subscription mapDtoToSubscription(SubscriptionDto subscriptionDto) {
        if (subscriptionDto != null) {
            Subscription subscription = new Subscription();
            subscription.setPlan(subscriptionDto.getPlan());
            subscription.setStatus(subscriptionDto.getStatus());
            subscription.setPaymentMethod(subscriptionDto.getPaymentMethod());
            return subscription;
        }
        return null;
    }

}

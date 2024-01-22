import java.util.List;
@Component
public class PersonUpdater {

     private final IRandomDataClient dataClient;
     private final PersonRepository personRepository;
     private final SubscriptionRepository subscriptionRepository;

     public PersonUpdater(IRandomDataClient dataClient, PersonRepository personRepository, SubscriptionRepository subscriptionRepository) {
          this.dataClient = dataClient;
          this.personRepository = personRepository;
          this.subscriptionRepository = subscriptionRepository;
     }
     @PostConstruct
     public void init() {
          // Zasilenie lokalnej bazy danych danymi 20 użytkowników
          updatePeople(20);
     }

     public void updatePeople(int size) {
          // Pobierz dane losowe dla zadanej ilości użytkowników
          List<RandomPersonDto> randomPeople = dataClient.getRandomPeople(size);

          // Iteruj przez dane użytkowników i zapisz je w lokalnej bazie danych
          for (RandomPersonDto randomPersonDto : randomPeople) {
               // Zapisz dane osobowe
               Person person = new Person();
               person.setFirstName(randomPersonDto.getFirstName());
               person.setLastName(randomPersonDto.getLastName());
               personRepository.save(person);

               // Zapisz subskrypcję
               RandomSubscriptionDto randomSubscriptionDto = randomPersonDto.getSubscription();
               if (randomSubscriptionDto != null) {
                    Subscription subscription = new Subscription();
                    subscription.setPlan(randomSubscriptionDto.getPlan());
                    subscription.setStatus(randomSubscriptionDto.getStatus());
                    subscription.setPaymentMethod(randomSubscriptionDto.getPaymentMethod());
                    subscription.setPerson(person); // Ustaw powiązanie z osobą
                    subscriptionRepository.save(subscription); // Zapisz subskrypcję
               }
          }
     }
}

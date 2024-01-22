@RestController
@RequiredArgsConstructor
public class PersonController{

    private PersonService service;

    @GetMapping("/api/v1/person")
    public ResponseEntity<List<PersonDto>> getAllPeople() {
        List<PersonDto> peopleDto = service.getAll();
        return ResponseEntity.ok(peopleDto);
    }

    @PostMapping("/api/v1/person")
    public ResponseEntity<Void> savePerson(@RequestBody PersonDto personDto) {
        service.save(personDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/person/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable int id) {
        PersonDto personDto = service.getById(id);

        if (personDto != null) {
            return ResponseEntity.ok(personDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/v1/person/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable int id, @RequestBody PersonDto personDto) {
        if (service.getById(id) != null) {
            service.update(id, personDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/v1/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        if (service.getById(id) != null) {
            service.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/api/v1/person/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDto>> getSubscriptions(@PathVariable int id) {
        List<SubscriptionDto> subscriptions = service.getSubscriptions(id);

        if (subscriptions != null) {
            return ResponseEntity.ok(subscriptions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

@Entity
@Getter
@Setter
public class Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String firstName;
    String lastName;

    @OneToMany(mappedBy = "person",cascade=CascadeType.ALL)
    private List<Subscription> subscription;
}
@Entity
@Getter
@Setter
public class Subscrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String plan;
    String status;
    String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
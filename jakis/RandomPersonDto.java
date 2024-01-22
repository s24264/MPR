@RequiredArgsConstructor
@Getter
@Setter
public class RandomPersonDto {
    Integer id;
    String firstName;
    String lastName;
    RandomAddressDto address;
}
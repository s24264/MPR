import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RandomDataClient implements IRandomDataClient {

    @Value("${random.data.url}")
    private String url;

    private final RestTemplate restTemplate;

    public RandomDataClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RandomPersonDto> getRandomPeople(int size) {
        String apiUrl = url + "/api/v2/users?size=" + size;
        RandomPersonDto[] peopleArray = restTemplate.getForObject(apiUrl, RandomPersonDto[].class);
        return Arrays.asList(peopleArray);
    }
}

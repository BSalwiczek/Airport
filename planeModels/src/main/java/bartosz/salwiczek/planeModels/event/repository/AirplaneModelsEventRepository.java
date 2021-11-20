package bartosz.salwiczek.planeModels.event.repository;

import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import bartosz.salwiczek.planeModels.event.dto.CreateAirplaneModelRestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AirplaneModelsEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public AirplaneModelsEventRepository(@Value("${planes.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(AirplaneModel model) {
        restTemplate.delete("/models/{name}", model.getName());
    }

    public void create(AirplaneModel model) {
        restTemplate.postForLocation("/models", CreateAirplaneModelRestRequest.entityToDtoMapper().apply(model));
    }
}

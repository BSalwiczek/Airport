package bartosz.salwiczek.planeModels.event.dto;

import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class CreateAirplaneModelRestRequest {
    private String name;

    public static Function<AirplaneModel, CreateAirplaneModelRestRequest> entityToDtoMapper() {
        return entity -> CreateAirplaneModelRestRequest.builder()
                .name(entity.getName())
                .build();
    }
}

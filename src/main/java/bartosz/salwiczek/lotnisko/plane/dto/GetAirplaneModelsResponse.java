package bartosz.salwiczek.lotnisko.plane.dto;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Setter
@Getter
@Builder
public class GetAirplaneModelsResponse {
    @Singular
    private List<String> airplaneModels;

    public static Function<Collection<AirplaneModel>, GetAirplaneModelsResponse> entityToDtoMapper() {
        return airplaneModel -> {
            GetAirplaneModelsResponseBuilder response = GetAirplaneModelsResponse.builder();
            airplaneModel.stream()
                    .map(AirplaneModel::getName)
                    .forEach(response::airplaneModel);
            return response.build();
        };
    }
}
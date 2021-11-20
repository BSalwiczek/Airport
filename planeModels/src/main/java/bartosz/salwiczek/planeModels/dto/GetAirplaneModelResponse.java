package bartosz.salwiczek.planeModels.dto;

import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Setter
@Getter
@Builder
public class GetAirplaneModelResponse {
    private String name;

    private Integer numberOfSeats;

    private Integer fuelCapacity;

    private Integer maxWeight;

    public static Function<AirplaneModel, GetAirplaneModelResponse> entityToDtoMapper() {
        return airplaneModel -> GetAirplaneModelResponse.builder()
                .name(airplaneModel.getName())
                .fuelCapacity(airplaneModel.getFuelCapacity())
                .numberOfSeats(airplaneModel.getNumberOfSeats())
                .maxWeight(airplaneModel.getMaxWeight())
                .build();
    }
}

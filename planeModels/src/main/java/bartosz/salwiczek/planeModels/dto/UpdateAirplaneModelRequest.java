package bartosz.salwiczek.planeModels.dto;

import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateAirplaneModelRequest {
    private Integer numberOfSeats;

    private Integer fuelCapacity;

    private Integer maxWeight;

    public static BiFunction<AirplaneModel, UpdateAirplaneModelRequest, AirplaneModel> dtoToEntityMapper() {
        return (airplaneModel, request) -> {
            airplaneModel.setNumberOfSeats(request.getNumberOfSeats());
            airplaneModel.setFuelCapacity(request.getFuelCapacity());
            airplaneModel.setMaxWeight(request.getMaxWeight());
            return airplaneModel;
        };
    }
}

package bartosz.salwiczek.lotnisko.plane.dto;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class CreateAirplaneModelRequest {
    private String name;

    private Integer numberOfSeats;

    private Integer fuelCapacity;

    private Integer maxWeight;

    public static Function<CreateAirplaneModelRequest, AirplaneModel> dtoToEntityMapper() {
        return request -> AirplaneModel.builder()
                .name(request.getName())
                .numberOfSeats(request.getNumberOfSeats())
                .fuelCapacity(request.getFuelCapacity())
                .maxWeight(request.getMaxWeight())
                .build();
    }
}

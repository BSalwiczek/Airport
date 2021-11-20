package bartosz.salwiczek.lotnisko.plane.dto;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
public class CreateAirplaneRequest {
    private int mileage;

    private String airplaneModel;

    public static Function<CreateAirplaneRequest, Airplane> dtoToEntityMapper(
            Function<String, AirplaneModel> airplaneModelFunction
    ) {
        return request -> Airplane.builder()
                .mileage(request.getMileage())
                .airplaneModel(airplaneModelFunction.apply(request.getAirplaneModel()))
                .build();
    }
}

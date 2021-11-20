package bartosz.salwiczek.airplanes.dto;

import bartosz.salwiczek.airplaneModels.entity.AirplaneModel;
import bartosz.salwiczek.airplanes.entity.Airplane;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
public class CreateAirplaneRequest {
    private int mileage;

    private String airplaneModel;

    private Integer productionYear;

    public static Function<CreateAirplaneRequest, Airplane> dtoToEntityMapper(
            Function<String, AirplaneModel> airplaneModelFunction
    ) {
        return request -> Airplane.builder()
                .mileage(request.getMileage())
                .productionYear(request.getProductionYear())
                .airplaneModel(airplaneModelFunction.apply(request.getAirplaneModel()))
                .build();
    }
}

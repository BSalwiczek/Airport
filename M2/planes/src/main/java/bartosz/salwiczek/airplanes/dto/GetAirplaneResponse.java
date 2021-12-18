package bartosz.salwiczek.airplanes.dto;

import bartosz.salwiczek.airplanes.entity.Airplane;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class GetAirplaneResponse {
    private Long id;

    private int mileage;

    private int productionYear;

    private String airplaneModel;

    public static Function<Airplane, GetAirplaneResponse> entityToDtoMapper() {
        return airplane -> GetAirplaneResponse.builder()
                .id(airplane.getId())
                .mileage(airplane.getMileage())
                .productionYear(airplane.getProductionYear())
                .airplaneModel(airplane.getAirplaneModel().getName())
                .build();
    }
}

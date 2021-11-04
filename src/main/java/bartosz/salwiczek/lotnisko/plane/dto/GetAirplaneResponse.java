package bartosz.salwiczek.lotnisko.plane.dto;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class GetAirplaneResponse {
    private Long serialNumber;

    private int mileage;

    private String airplaneModel;

    public static Function<Airplane, GetAirplaneResponse> entityToDtoMapper() {
        return airplane -> GetAirplaneResponse.builder()
                .serialNumber(airplane.getSerialNumber())
                .mileage(airplane.getMileage())
                .airplaneModel(airplane.getAirplaneModel().getName())
                .build();
    }
}

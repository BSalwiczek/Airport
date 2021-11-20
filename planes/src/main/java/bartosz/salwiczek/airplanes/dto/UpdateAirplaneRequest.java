package bartosz.salwiczek.airplanes.dto;

import bartosz.salwiczek.airplanes.entity.Airplane;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateAirplaneRequest {
    private Integer mileage;

    private Integer productionYear;

    public static BiFunction<Airplane, UpdateAirplaneRequest, Airplane> dtoToEntityMapper() {
        return (airplane, request) -> {
            airplane.setMileage(request.getMileage());
            airplane.setProductionYear(request.getProductionYear());
            return airplane;
        };
    }
}

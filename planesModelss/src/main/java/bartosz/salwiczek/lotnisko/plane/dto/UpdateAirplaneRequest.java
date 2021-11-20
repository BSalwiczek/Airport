package bartosz.salwiczek.lotnisko.plane.dto;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

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

    public static BiFunction<Airplane, UpdateAirplaneRequest, Airplane> dtoToEntityMapper() {
        return (airplane, request) -> {
            airplane.setMileage(request.getMileage());
            return airplane;
        };
    }
}

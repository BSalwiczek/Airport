package bartosz.salwiczek.lotnisko.plane.dto;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
public class GetAirplanesResponse {
    @Singular
    private List<Long> airplanes;

    public static Function<Collection<Airplane>, GetAirplanesResponse> entityToDtoMapper() {
        return airplane -> {
            GetAirplanesResponseBuilder response = GetAirplanesResponse.builder();
            airplane.stream()
                    .map(Airplane::getId)
                    .forEach(response::airplane);
            return response.build();
        };
    }

}

package bartosz.salwiczek.airplaneModels.dto;

import bartosz.salwiczek.airplaneModels.entity.AirplaneModel;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class CreateAirplaneModelRequest {
    private String name;

    public static Function<CreateAirplaneModelRequest, AirplaneModel> dtoToEntityMapper() {
        return request -> AirplaneModel.builder()
                .name(request.getName())
                .build();
    }
}

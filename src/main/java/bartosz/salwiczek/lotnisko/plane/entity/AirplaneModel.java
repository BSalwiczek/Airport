package bartosz.salwiczek.lotnisko.plane.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class AirplaneModel implements Serializable {
    private String name;

    private Integer numberOfSeats;

    private Integer fuelCapacity;

    private Integer maxWeight;
}

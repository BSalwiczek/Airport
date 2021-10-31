package bartosz.salwiczek.lotnisko.plane.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Airplane implements Serializable {
    private Long serialNumber;

    private int mileage;

    private AirplaneModel airplaneModel;
}

package bartosz.salwiczek.lotnisko.plane.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="airplane_models")
public class AirplaneModel implements Serializable {
    @Id
    private String name;

    private Integer numberOfSeats;

    private Integer fuelCapacity;

    private Integer maxWeight;

    @OneToMany
    @JoinColumn(name="airplanes")
    private List<Airplane> airplanes;
}

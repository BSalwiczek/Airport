package bartosz.salwiczek.planeModels.entity;

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

    @Column
    private Integer numberOfSeats;

    @Column
    private Integer fuelCapacity;

    @Column
    private Integer maxWeight;

//    @OneToMany(cascade=CascadeType.ALL, mappedBy = "airplaneModel", fetch=FetchType.LAZY)
////    @JoinColumn(name="airplanes")
//    private List<Airplane> airplanes;
}

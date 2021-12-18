package bartosz.salwiczek.airplaneModels.entity;

import bartosz.salwiczek.airplanes.entity.Airplane;
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

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "airplaneModel", fetch=FetchType.LAZY)
//    @JoinColumn(name="airplanes")
    private List<Airplane> airplanes;
}

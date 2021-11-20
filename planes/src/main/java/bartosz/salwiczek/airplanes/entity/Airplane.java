package bartosz.salwiczek.airplanes.entity;

import bartosz.salwiczek.airplaneModels.entity.AirplaneModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode()
@ToString(callSuper = true)
@Entity
@Table(name="airplanes")
public class Airplane implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column
    private Integer mileage;

    @Column
    private Integer productionYear;

    @ManyToOne
    @JoinColumn(name="airplane_model")
    private AirplaneModel airplaneModel;
}

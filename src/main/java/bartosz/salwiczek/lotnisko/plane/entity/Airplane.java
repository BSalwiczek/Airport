package bartosz.salwiczek.lotnisko.plane.entity;

import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="airplanes")
public class Airplane implements Serializable {
    @Id
    private Long serialNumber;

    private Integer mileage;

    @ManyToOne
    @JoinColumn(name="airplane_models")
    private AirplaneModel airplaneModel;
}

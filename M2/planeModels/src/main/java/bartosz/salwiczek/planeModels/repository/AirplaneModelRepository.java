package bartosz.salwiczek.planeModels.repository;

import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirplaneModelRepository extends JpaRepository<AirplaneModel, Long> {

    Optional<AirplaneModel> findByName(String name);

    List<AirplaneModel> findAll();
}
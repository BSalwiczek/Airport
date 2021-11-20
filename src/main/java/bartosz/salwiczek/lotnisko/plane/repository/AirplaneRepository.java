package bartosz.salwiczek.lotnisko.plane.repository;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Optional<Airplane> findById(Long Id);

    List<Airplane> findAll();

}

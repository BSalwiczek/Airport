package bartosz.salwiczek.lotnisko.plane.service;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {
    private AirplaneRepository repository;

    @Autowired
    public AirplaneService(AirplaneRepository repository) {
        this.repository = repository;
    }

    public Optional<Airplane> find(Long Id) {
        return repository.findById(Id);
    }

    public List<Airplane> findAll() {
        return repository.findAll();
    }

    public void create(Airplane airplane) {
        repository.save(airplane);
    }

    public void update(Airplane airplane) {
        repository.save(airplane);
    }

    public void delete(Airplane airplane) {
        repository.delete(airplane);
    }
}

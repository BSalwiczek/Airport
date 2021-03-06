package bartosz.salwiczek.lotnisko.plane.service;

import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import bartosz.salwiczek.lotnisko.plane.repository.AirplaneModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneModelService {
    private AirplaneModelRepository repository;

    @Autowired
    public AirplaneModelService(AirplaneModelRepository repository) {
        this.repository = repository;
    }

    public Optional<AirplaneModel> find(String name) {
        return repository.find(name);
    }

    public List<AirplaneModel> findAll() {
        return repository.findAll();
    }

    public void create(AirplaneModel airplane) {
        repository.create(airplane);
    }

    public void delete(AirplaneModel airplane) {
        repository.delete(airplane);
    }
}

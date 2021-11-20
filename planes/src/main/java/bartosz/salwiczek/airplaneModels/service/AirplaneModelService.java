package bartosz.salwiczek.airplaneModels.service;

import bartosz.salwiczek.airplaneModels.entity.AirplaneModel;
import bartosz.salwiczek.airplaneModels.repository.AirplaneModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneModelService {
    private AirplaneModelRepository repository;

    @Autowired
    public AirplaneModelService(AirplaneModelRepository repository) {
        this.repository = repository;
    }

    public Optional<AirplaneModel> find(String name) {
        return repository.findByName(name);
    }

    public void create(AirplaneModel airplaneModel) {
        if(find(airplaneModel.getName()).isPresent()) {
            return;
        }
        repository.save(airplaneModel);
    }

    public void delete(AirplaneModel airplaneModel) {
        repository.delete(airplaneModel);
    }
}

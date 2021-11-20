package bartosz.salwiczek.planeModels.service;

import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import bartosz.salwiczek.planeModels.event.repository.AirplaneModelsEventRepository;
import bartosz.salwiczek.planeModels.repository.AirplaneModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneModelService {
    private AirplaneModelRepository repository;

    private AirplaneModelsEventRepository eventRepository;

    @Autowired
    public AirplaneModelService(AirplaneModelRepository repository, AirplaneModelsEventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.repository = repository;
    }

    public Optional<AirplaneModel> find(String name) {
        return repository.findByName(name);
    }

    public List<AirplaneModel> findAll() {
        return repository.findAll();
    }

    public void create(AirplaneModel airplaneModel) {
        repository.save(airplaneModel);
        eventRepository.create(airplaneModel);
    }

    public void update(AirplaneModel airplaneModel) {
        repository.save(airplaneModel);
    }

    public void delete(AirplaneModel airplaneModel) {
        repository.delete(airplaneModel);
        eventRepository.delete(airplaneModel);
    }
}

package bartosz.salwiczek.lotnisko.plane.repository;

import bartosz.salwiczek.lotnisko.datastore.DataStore;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AirplaneModelRepository {
    private DataStore datastore;

    @Autowired
    public AirplaneModelRepository(DataStore datastore) {
        this.datastore = datastore;
    }

    public Optional<AirplaneModel> find(String name) {
        return datastore.findAirplaneModel(name);
    }

    public List<AirplaneModel> findAll() {
        return datastore.findAllAirplaneModels();
    }

    public void create(AirplaneModel airplane) {
        datastore.createAirplaneModel(airplane);
    }

    public void delete(AirplaneModel airplane) {
        datastore.deleteAirplaneModel(airplane);
    }
}

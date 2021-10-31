package bartosz.salwiczek.lotnisko.plane.repository;

import bartosz.salwiczek.lotnisko.datastore.DataStore;
import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AirplaneRepository {
    private DataStore datastore;

    @Autowired
    public AirplaneRepository(DataStore datastore) {
        this.datastore = datastore;
    }

    public Optional<Airplane> find(Long serialNumber) {
        return datastore.findAirplane(serialNumber);
    }

    public List<Airplane> findAll() {
        return datastore.findAllAirplanes();
    }

    public void create(Airplane airplane) {
        datastore.createAirplane(airplane);
    }

    public void update(Airplane airplane) {
        datastore.updateAirplane(airplane);
    }

    public void delete(Airplane airplane) {
        datastore.deleteAirplane(airplane);
    }

}

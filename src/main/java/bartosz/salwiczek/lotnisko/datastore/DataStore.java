package bartosz.salwiczek.lotnisko.datastore;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import bartosz.salwiczek.lotnisko.serialization.CloningUtility;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * For the sake of simplification instead of using real database this example is using an data source object which
 * should be put in servlet context in a single instance. In order to avoid {@link
 * java.util.ConcurrentModificationException} all methods are synchronized. Normally synchronization would be carried on
 * by the database server.
 */
@Log
@Component
public class DataStore {
    private Set<AirplaneModel> airplanesModels = new HashSet<>();

    private Set<Airplane> airplanes = new HashSet<>();

    public synchronized List<AirplaneModel> findAllAirplaneModels() {
        return new ArrayList<>(airplanesModels);
    }

    public synchronized Optional<AirplaneModel> findAirplaneModel(String name) {
        return airplanesModels.stream()
                .filter(airplaneModel -> airplaneModel.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createAirplaneModel(AirplaneModel airplaneModel) throws IllegalArgumentException {
        findAirplaneModel(airplaneModel.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The Airplane model name \"%s\" is not unique", airplaneModel.getName()));
                },
                () -> airplanesModels.add(CloningUtility.clone(airplaneModel)));
    }

    public synchronized void deleteAirplaneModel(AirplaneModel airplaneModel) throws IllegalArgumentException {
        findAirplaneModel(airplaneModel.getName()).ifPresentOrElse(
                original -> airplanes.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The Airplane model with name \"%s\" does not exist", airplaneModel.getName()));
                });
    }

    public synchronized List<Airplane> findAllAirplanes() {
        return airplanes.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Airplane> findAirplane(Long id) {
        return airplanes.stream()
                .filter(airplane -> airplane.getSerialNumber().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createAirplane(Airplane airplane) throws IllegalArgumentException {
        airplane.setSerialNumber(findAllAirplanes().stream()
                .mapToLong(Airplane::getSerialNumber)
                .max().orElse(0) + 1);
        airplanes.add(CloningUtility.clone(airplane));
    }

    public synchronized void updateAirplane(Airplane airplane) throws IllegalArgumentException {
        findAirplane(airplane.getSerialNumber()).ifPresentOrElse(
                original -> {
                    airplanes.remove(original);
                    airplanes.add(CloningUtility.clone(airplane));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The Airplane with serial number \"%d\" does not exist", airplane.getSerialNumber()));
                });
    }

    public synchronized void deleteAirplane(Airplane airplane) throws IllegalArgumentException {
        findAirplane(airplane.getSerialNumber()).ifPresentOrElse(
                original -> airplanes.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The Airplane with serial number \"%d\" does not exist", airplane.getSerialNumber()));
                });
    }
}

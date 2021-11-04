package bartosz.salwiczek.lotnisko.configuration;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import bartosz.salwiczek.lotnisko.plane.service.AirplaneModelService;
import bartosz.salwiczek.lotnisko.plane.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {

    private final AirplaneService airplaneService;

    private final AirplaneModelService airplaneModelService;

    @Autowired
    public InitializeData(AirplaneService airplaneService, AirplaneModelService airplaneModelService) {
        this.airplaneModelService = airplaneModelService;
        this.airplaneService = airplaneService;
    }

    @PostConstruct
    private synchronized void init() {
        AirplaneModel boeing747 = AirplaneModel.builder().name("Boeing747").fuelCapacity(1000).maxWeight(345).numberOfSeats(200).build();
        AirplaneModel boeing737 = AirplaneModel.builder().name("Boeing737").fuelCapacity(2000).maxWeight(245).numberOfSeats(140).build();
        AirplaneModel airbusA320 = AirplaneModel.builder().name("AirbusA320").fuelCapacity(1200).maxWeight(278).numberOfSeats(160).build();

        airplaneModelService.create(boeing737);
        airplaneModelService.create(boeing747);
        airplaneModelService.create(airbusA320);

        Airplane a1 = Airplane.builder().serialNumber(1L).airplaneModel(boeing737).mileage(10000).build();
        Airplane a2 = Airplane.builder().serialNumber(2L).airplaneModel(boeing747).mileage(12312).build();
        Airplane a3 = Airplane.builder().serialNumber(3L).airplaneModel(airbusA320).mileage(42233).build();

        airplaneService.create(a1);
        airplaneService.create(a2);
        airplaneService.create(a3);
    }

}

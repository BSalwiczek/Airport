package bartosz.salwiczek.planeModels.configuration;

import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import bartosz.salwiczek.planeModels.service.AirplaneModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {
    private final AirplaneModelService airplaneModelService;

    @Autowired
    public InitializeData(AirplaneModelService airplaneModelService) {
        this.airplaneModelService = airplaneModelService;
    }

    @PostConstruct
    private synchronized void init() {
        AirplaneModel boeing747 = AirplaneModel.builder().name("Boeing747").fuelCapacity(1000).maxWeight(345).numberOfSeats(200).build();
        AirplaneModel boeing737 = AirplaneModel.builder().name("Boeing737").fuelCapacity(2000).maxWeight(245).numberOfSeats(140).build();
        AirplaneModel airbusA320 = AirplaneModel.builder().name("AirbusA320").fuelCapacity(1200).maxWeight(278).numberOfSeats(160).build();

        airplaneModelService.create(boeing737);
        airplaneModelService.create(boeing747);
        airplaneModelService.create(airbusA320);
    }

}

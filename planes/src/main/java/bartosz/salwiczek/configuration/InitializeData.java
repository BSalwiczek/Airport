package bartosz.salwiczek.configuration;

import bartosz.salwiczek.airplaneModels.entity.AirplaneModel;
import bartosz.salwiczek.airplaneModels.service.AirplaneModelService;
import bartosz.salwiczek.airplanes.entity.Airplane;
import bartosz.salwiczek.airplanes.service.AirplaneService;
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
        AirplaneModel boeing747 = AirplaneModel.builder().name("Boeing747").build();
        AirplaneModel boeing737 = AirplaneModel.builder().name("Boeing737").build();
        AirplaneModel airbusA320 = AirplaneModel.builder().name("AirbusA320").build();

        airplaneModelService.create(boeing737);
        airplaneModelService.create(boeing747);
        airplaneModelService.create(airbusA320);

        Airplane a1 = Airplane.builder().id(1L).airplaneModel(boeing737).mileage(10000).build();
        Airplane a2 = Airplane.builder().id(2L).airplaneModel(boeing747).mileage(12312).build();
        Airplane a3 = Airplane.builder().id(3L).airplaneModel(airbusA320).mileage(42233).build();

        airplaneService.create(a1);
        airplaneService.create(a2);
        airplaneService.create(a3);
    }

}

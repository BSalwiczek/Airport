package bartosz.salwiczek.airplaneModels.controller;

import bartosz.salwiczek.airplaneModels.dto.CreateAirplaneModelRequest;
import bartosz.salwiczek.airplaneModels.entity.AirplaneModel;
import bartosz.salwiczek.airplaneModels.service.AirplaneModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/models")
public class AirplaneModelController {
    private final AirplaneModelService airplaneModelService;

    @Autowired
    public AirplaneModelController(AirplaneModelService airplaneModelService) {
        this.airplaneModelService = airplaneModelService;
    }

    @PostMapping
    public ResponseEntity<Void> createAirplaneModel(@RequestBody CreateAirplaneModelRequest request, UriComponentsBuilder builder) {
        if (airplaneModelService.find(request.getName()).isPresent()) {
            return ResponseEntity.ok().build();
        }

        AirplaneModel airplaneModel = CreateAirplaneModelRequest.dtoToEntityMapper().apply(request);
        airplaneModelService.create(airplaneModel);

        return ResponseEntity
                .created(builder.pathSegment("api", "airplaneModel", "{modelName}").buildAndExpand(airplaneModel.getName()).toUri())
                .build();

    }

    @DeleteMapping("{modelName}")
    public ResponseEntity<Void> deleteAirplaneModelRequest(@PathVariable("modelName") String modelName) {
        Optional<AirplaneModel> airplaneModel = airplaneModelService.find(modelName);
        if (airplaneModel.isPresent()) {
            airplaneModelService.delete(airplaneModel.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
}


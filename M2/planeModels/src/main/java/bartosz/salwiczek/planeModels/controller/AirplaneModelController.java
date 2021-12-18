package bartosz.salwiczek.planeModels.controller;

import bartosz.salwiczek.planeModels.dto.CreateAirplaneModelRequest;
import bartosz.salwiczek.planeModels.dto.GetAirplaneModelResponse;
import bartosz.salwiczek.planeModels.dto.GetAirplaneModelsResponse;
import bartosz.salwiczek.planeModels.dto.UpdateAirplaneModelRequest;
import bartosz.salwiczek.planeModels.entity.AirplaneModel;
import bartosz.salwiczek.planeModels.service.AirplaneModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/models")
public class AirplaneModelController {
    private AirplaneModelService airplaneModelService;

    @Autowired
    public AirplaneModelController(AirplaneModelService airplaneModelService) {
        this.airplaneModelService = airplaneModelService;
    }

    @GetMapping("{modelName}")
    public ResponseEntity<GetAirplaneModelResponse> getAirplaneModels(@PathVariable("modelName") String modelName) {
        Optional<AirplaneModel> airplaneModelOptional = airplaneModelService.find(modelName);
        if (airplaneModelOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(GetAirplaneModelResponse.entityToDtoMapper().apply(airplaneModelOptional.get()));
    }

    @GetMapping
    public ResponseEntity<GetAirplaneModelsResponse> getAirplaneModels() {
        return ResponseEntity.ok(GetAirplaneModelsResponse.entityToDtoMapper().apply(airplaneModelService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> createAirplaneModel(@RequestBody CreateAirplaneModelRequest request, UriComponentsBuilder builder) {
        if (airplaneModelService.find(request.getName()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        AirplaneModel airplaneModel = CreateAirplaneModelRequest.dtoToEntityMapper().apply(request);
        airplaneModelService.create(airplaneModel);

        return ResponseEntity
                .created(builder.pathSegment("api", "airplaneModel", "{modelName}").buildAndExpand(airplaneModel.getName()).toUri())
                .build();

    }

    @PutMapping("{modelName}")
    public ResponseEntity<Void> updateAirplaneModelRequest(@RequestBody UpdateAirplaneModelRequest request, @PathVariable("modelName") String modelName) {
        Optional<AirplaneModel> airplaneModel = airplaneModelService.find(modelName);
        if (airplaneModel.isPresent()) {
            UpdateAirplaneModelRequest.dtoToEntityMapper().apply(airplaneModel.get(), request);
            airplaneModelService.update(airplaneModel.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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


package bartosz.salwiczek.lotnisko.plane.controller;

import bartosz.salwiczek.lotnisko.plane.dto.*;
import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import bartosz.salwiczek.lotnisko.plane.service.AirplaneModelService;
import bartosz.salwiczek.lotnisko.plane.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/models")
public class AirplaneModelController {
    private AirplaneService airplaneService;

    private AirplaneModelService airplaneModelService;

    @Autowired
    public AirplaneModelController(AirplaneService airplaneService, AirplaneModelService airplaneModelService) {
        this.airplaneService = airplaneService;
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


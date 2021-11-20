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
import java.util.logging.Logger;

@RestController
@RequestMapping("api/models/{modelName}/airplanes")
public class AirplaneController {
    private AirplaneService airplaneService;

    private AirplaneModelService airplaneModelService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService, AirplaneModelService airplaneModelService) {
        this.airplaneService = airplaneService;
        this.airplaneModelService = airplaneModelService;
    }

    @GetMapping
    public ResponseEntity<GetAirplanesResponse> getAirplanes(@PathVariable("modelName") String airplaneModel) {
        return ResponseEntity.ok(GetAirplanesResponse.entityToDtoMapper().apply(airplaneService.findAll().stream()
                .filter((airplane -> airplane.getAirplaneModel().getName().equals(airplaneModel))).toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAirplaneResponse> getAirplane(@PathVariable("id") long id, @PathVariable("modelName") String modelName) {
        Optional<Airplane> airplaneOptional = airplaneService.find(id);
        if(airplaneOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if(!airplaneOptional.get().getAirplaneModel().getName().equals(modelName)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(GetAirplaneResponse.entityToDtoMapper().apply(airplaneOptional.get()));
    }

    @PostMapping
    public ResponseEntity<Void> createAirplane(@RequestBody CreateAirplaneRequest request, UriComponentsBuilder builder, @PathVariable String modelName) {
        if(!request.getAirplaneModel().equals(modelName)) {
            return ResponseEntity.badRequest().build();
        }

        Airplane airplane = CreateAirplaneRequest.dtoToEntityMapper(name -> airplaneModelService.find(name).orElseThrow()).apply(request);
        airplaneService.create(airplane);

        return ResponseEntity
                .created(builder.pathSegment("api", "airplane", "{id}").buildAndExpand(airplane.getId()).toUri())
                .build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAirplaneRequest(@RequestBody UpdateAirplaneRequest request, @PathVariable("id") Long id, @PathVariable String modelName) {
        Optional<Airplane> airplane = airplaneService.find(id);
        if (airplane.isPresent()) {
            if(!airplane.get().getAirplaneModel().getName().equals(modelName)) {
                return ResponseEntity.notFound().build();
            }
            UpdateAirplaneRequest.dtoToEntityMapper().apply(airplane.get(), request);
            airplaneService.update(airplane.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAirplaneRequest(@PathVariable("id") Long id, @PathVariable String modelName) {
        Optional<Airplane> airplane = airplaneService.find(id);
        if (airplane.isPresent()) {
            airplaneService.delete(airplane.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
}

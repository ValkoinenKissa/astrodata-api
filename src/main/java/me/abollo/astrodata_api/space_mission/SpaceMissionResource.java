package me.abollo.astrodata_api.space_mission;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/spaceMissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpaceMissionResource {

    private final SpaceMissionService spaceMissionService;

    public SpaceMissionResource(final SpaceMissionService spaceMissionService) {
        this.spaceMissionService = spaceMissionService;
    }

    @GetMapping
    public ResponseEntity<List<SpaceMissionDTO>> getAllSpaceMissions() {
        return ResponseEntity.ok(spaceMissionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceMissionDTO> getSpaceMission(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(spaceMissionService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSpaceMission(
            @RequestBody @Valid final SpaceMissionDTO spaceMissionDTO) {
        final Long createdId = spaceMissionService.create(spaceMissionDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSpaceMission(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SpaceMissionDTO spaceMissionDTO) {
        spaceMissionService.update(id, spaceMissionDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSpaceMission(@PathVariable(name = "id") final Long id) {
        spaceMissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

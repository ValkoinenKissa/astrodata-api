package me.abollo.astrodata_api.cosmic_event;

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
@RequestMapping(value = "/api/cosmicEvents", produces = MediaType.APPLICATION_JSON_VALUE)
public class CosmicEventResource {

    private final CosmicEventService cosmicEventService;

    public CosmicEventResource(final CosmicEventService cosmicEventService) {
        this.cosmicEventService = cosmicEventService;
    }

    @GetMapping
    public ResponseEntity<List<CosmicEventDTO>> getAllCosmicEvents() {
        return ResponseEntity.ok(cosmicEventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CosmicEventDTO> getCosmicEvent(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(cosmicEventService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCosmicEvent(
            @RequestBody @Valid final CosmicEventDTO cosmicEventDTO) {
        final Long createdId = cosmicEventService.create(cosmicEventDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCosmicEvent(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CosmicEventDTO cosmicEventDTO) {
        cosmicEventService.update(id, cosmicEventDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCosmicEvent(@PathVariable(name = "id") final Long id) {
        cosmicEventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

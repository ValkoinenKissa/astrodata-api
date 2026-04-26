package me.abollo.astrodata_api.exoplanet;

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
@RequestMapping(value = "/api/exoplanets", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExoplanetResource {

    private final ExoplanetService exoplanetService;

    public ExoplanetResource(final ExoplanetService exoplanetService) {
        this.exoplanetService = exoplanetService;
    }

    @GetMapping
    public ResponseEntity<List<ExoplanetDTO>> getAllExoplanets() {
        return ResponseEntity.ok(exoplanetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExoplanetDTO> getExoplanet(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(exoplanetService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createExoplanet(
            @RequestBody @Valid final ExoplanetDTO exoplanetDTO) {
        final Long createdId = exoplanetService.create(exoplanetDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateExoplanet(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ExoplanetDTO exoplanetDTO) {
        exoplanetService.update(id, exoplanetDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteExoplanet(@PathVariable(name = "id") final Long id) {
        exoplanetService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

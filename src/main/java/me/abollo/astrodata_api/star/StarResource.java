package me.abollo.astrodata_api.star;

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
@RequestMapping(value = "/api/stars", produces = MediaType.APPLICATION_JSON_VALUE)
public class StarResource {

    private final StarService starService;

    public StarResource(final StarService starService) {
        this.starService = starService;
    }

    @GetMapping
    public ResponseEntity<List<StarDTO>> getAllStars() {
        return ResponseEntity.ok(starService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarDTO> getStar(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(starService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createStar(@RequestBody @Valid final StarDTO starDTO) {
        final Long createdId = starService.create(starDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateStar(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final StarDTO starDTO) {
        starService.update(id, starDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteStar(@PathVariable(name = "id") final Long id) {
        starService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

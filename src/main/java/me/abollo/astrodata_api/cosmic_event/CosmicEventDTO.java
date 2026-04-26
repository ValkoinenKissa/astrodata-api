package me.abollo.astrodata_api.cosmic_event;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CosmicEventDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String eventType;

    private LocalDate eventDate;

    private Double magnitude;

    @Size(max = 255)
    private String description;

    private Long star;

}

package me.abollo.astrodata_api.space_mission;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SpaceMissionDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String agency;

    private LocalDate launchDate;

    @Size(max = 255)
    private String status;

    @Size(max = 255)
    private String description;

}

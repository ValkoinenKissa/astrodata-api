package me.abollo.astrodata_api.exoplanet;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExoplanetDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private Double massEarth;

    private Double radiusEarth;

    private Double orbitalPeriodDays;

    private Integer discoveryYear;

    @JsonProperty("isInHabitableZone")
    private Boolean isInHabitableZone;

    private Long star;

    private List<Long> spaceMissions;

}

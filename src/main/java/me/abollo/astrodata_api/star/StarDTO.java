package me.abollo.astrodata_api.star;

import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StarDTO {

    private Long id;

    @Size(max = 255)
    private String catalogueId;

    @Size(max = 255)
    private String spectralType;

    private Double massSolar;

    private Double radiusSolar;

    private Double distanceLy;

    @Size(max = 255)
    private String constellation;

    private List<Long> spaceMissions;

}

package me.abollo.astrodata_api.exoplanet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import me.abollo.astrodata_api.space_mission.SpaceMission;
import me.abollo.astrodata_api.star.Star;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "exoplanets")                     // ← snake_case
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Exoplanet {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double massEarth;

    @Column
    private Double radiusEarth;

    @Column
    private Double orbitalPeriodDays;

    @Column
    private Integer discoveryYear;

    @Column(columnDefinition = "tinyint", length = 1)
    private Boolean isInHabitableZone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "star_id")
    private Star star;

    @ManyToMany
    @JoinTable(
            name = "exoplanet_space_missions",
            joinColumns = @JoinColumn(name = "exoplanet_id"),
            inverseJoinColumns = @JoinColumn(name = "space_mission_id")
    )
    private Set<SpaceMission> spaceMissions = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
package me.abollo.astrodata_api.star;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import me.abollo.astrodata_api.cosmic_event.CosmicEvent;
import me.abollo.astrodata_api.exoplanet.Exoplanet;
import me.abollo.astrodata_api.space_mission.SpaceMission;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "stars")                          // ← snake_case
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Star {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String catalogueId;

    @Column
    private String spectralType;

    @Column
    private Double massSolar;

    @Column
    private Double radiusSolar;

    @Column
    private Double temperatureK;

    @Column
    private Double distanceLy;

    @Column
    private String constellation;

    @OneToMany(mappedBy = "star")
    private Set<Exoplanet> exoplanets = new HashSet<>();

    @OneToMany(mappedBy = "star")
    private Set<CosmicEvent> cosmicEvents = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "star_space_missions",
            joinColumns = @JoinColumn(name = "star_id"),
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
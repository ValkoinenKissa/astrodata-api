package me.abollo.astrodata_api.space_mission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import me.abollo.astrodata_api.exoplanet.Exoplanet;
import me.abollo.astrodata_api.star.Star;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "SpaceMissions")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class SpaceMission {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String agency;

    @Column
    private LocalDate launchDate;

    @Column
    private String status;

    @Column(name = "\"description\"")
    private String description;

    @ManyToMany(mappedBy = "spaceMissions")
    private Set<Star> stars = new HashSet<>();

    @ManyToMany(mappedBy = "spaceMissions")
    private Set<Exoplanet> exoplanets = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}

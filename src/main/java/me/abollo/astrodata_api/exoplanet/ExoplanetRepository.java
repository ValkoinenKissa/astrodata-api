package me.abollo.astrodata_api.exoplanet;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExoplanetRepository extends JpaRepository<Exoplanet, Long> {

    Exoplanet findFirstByStarId(Long id);

    List<Exoplanet> findAllBySpaceMissionsId(Long id);

}

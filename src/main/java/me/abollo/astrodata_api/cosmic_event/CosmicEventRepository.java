package me.abollo.astrodata_api.cosmic_event;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CosmicEventRepository extends JpaRepository<CosmicEvent, Long> {

    CosmicEvent findFirstByStarId(Long id);

}

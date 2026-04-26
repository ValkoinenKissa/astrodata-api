package me.abollo.astrodata_api.star;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StarRepository extends JpaRepository<Star, Long> {

    List<Star> findAllBySpaceMissionsId(Long id);

}

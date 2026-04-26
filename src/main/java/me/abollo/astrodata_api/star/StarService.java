package me.abollo.astrodata_api.star;

import java.util.HashSet;
import java.util.List;
import me.abollo.astrodata_api.events.BeforeDeleteSpaceMission;
import me.abollo.astrodata_api.events.BeforeDeleteStar;
import me.abollo.astrodata_api.space_mission.SpaceMission;
import me.abollo.astrodata_api.space_mission.SpaceMissionRepository;
import me.abollo.astrodata_api.util.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class StarService {

    private final StarRepository starRepository;
    private final SpaceMissionRepository spaceMissionRepository;
    private final ApplicationEventPublisher publisher;

    public StarService(final StarRepository starRepository,
            final SpaceMissionRepository spaceMissionRepository,
            final ApplicationEventPublisher publisher) {
        this.starRepository = starRepository;
        this.spaceMissionRepository = spaceMissionRepository;
        this.publisher = publisher;
    }

    public List<StarDTO> findAll() {
        final List<Star> stars = starRepository.findAll(Sort.by("id"));
        return stars.stream()
                .map(star -> mapToDTO(star, new StarDTO()))
                .toList();
    }

    public StarDTO get(final Long id) {
        return starRepository.findById(id)
                .map(star -> mapToDTO(star, new StarDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final StarDTO starDTO) {
        final Star star = new Star();
        mapToEntity(starDTO, star);
        return starRepository.save(star).getId();
    }

    public void update(final Long id, final StarDTO starDTO) {
        final Star star = starRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(starDTO, star);
        starRepository.save(star);
    }

    public void delete(final Long id) {
        final Star star = starRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeleteStar(id));
        starRepository.delete(star);
    }

    private StarDTO mapToDTO(final Star star, final StarDTO starDTO) {
        starDTO.setId(star.getId());
        starDTO.setName(star.getName());
        starDTO.setCatalogueId(star.getCatalogueId());
        starDTO.setSpectralType(star.getSpectralType());
        starDTO.setMassSolar(star.getMassSolar());
        starDTO.setRadiusSolar(star.getRadiusSolar());
        starDTO.setTemperatureK(star.getTemperatureK());
        starDTO.setDistanceLy(star.getDistanceLy());
        starDTO.setConstellation(star.getConstellation());
        starDTO.setSpaceMissions(star.getSpaceMissions().stream()
                .map(spaceMission -> spaceMission.getId())
                .toList());
        return starDTO;
    }

    private Star mapToEntity(final StarDTO starDTO, final Star star) {
        star.setCatalogueId(starDTO.getCatalogueId());
        star.setName(starDTO.getName());
        star.setSpectralType(starDTO.getSpectralType());
        star.setMassSolar(starDTO.getMassSolar());
        star.setRadiusSolar(starDTO.getRadiusSolar());
        star.setTemperatureK(starDTO.getTemperatureK());
        star.setDistanceLy(starDTO.getDistanceLy());
        star.setConstellation(starDTO.getConstellation());
        final List<SpaceMission> spaceMissions = spaceMissionRepository.findAllById(
                starDTO.getSpaceMissions() == null ? List.of() : starDTO.getSpaceMissions());
        if (spaceMissions.size() != (starDTO.getSpaceMissions() == null ? 0 : starDTO.getSpaceMissions().size())) {
            throw new NotFoundException("one of spaceMissions not found");
        }
        star.setSpaceMissions(new HashSet<>(spaceMissions));
        return star;
    }

    @EventListener(BeforeDeleteSpaceMission.class)
    public void on(final BeforeDeleteSpaceMission event) {
        // remove many-to-many relations at owning side
        starRepository.findAllBySpaceMissionsId(event.getId()).forEach(star ->
                star.getSpaceMissions().removeIf(spaceMission -> spaceMission.getId().equals(event.getId())));
    }

}

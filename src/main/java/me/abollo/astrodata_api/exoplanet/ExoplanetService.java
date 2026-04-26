package me.abollo.astrodata_api.exoplanet;

import java.util.HashSet;
import java.util.List;
import me.abollo.astrodata_api.events.BeforeDeleteSpaceMission;
import me.abollo.astrodata_api.events.BeforeDeleteStar;
import me.abollo.astrodata_api.space_mission.SpaceMission;
import me.abollo.astrodata_api.space_mission.SpaceMissionRepository;
import me.abollo.astrodata_api.star.Star;
import me.abollo.astrodata_api.star.StarRepository;
import me.abollo.astrodata_api.util.NotFoundException;
import me.abollo.astrodata_api.util.ReferencedException;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class ExoplanetService {

    private final ExoplanetRepository exoplanetRepository;
    private final StarRepository starRepository;
    private final SpaceMissionRepository spaceMissionRepository;

    public ExoplanetService(final ExoplanetRepository exoplanetRepository,
            final StarRepository starRepository,
            final SpaceMissionRepository spaceMissionRepository) {
        this.exoplanetRepository = exoplanetRepository;
        this.starRepository = starRepository;
        this.spaceMissionRepository = spaceMissionRepository;
    }

    public List<ExoplanetDTO> findAll() {
        final List<Exoplanet> exoplanets = exoplanetRepository.findAll(Sort.by("id"));
        return exoplanets.stream()
                .map(exoplanet -> mapToDTO(exoplanet, new ExoplanetDTO()))
                .toList();
    }

    public ExoplanetDTO get(final Long id) {
        return exoplanetRepository.findById(id)
                .map(exoplanet -> mapToDTO(exoplanet, new ExoplanetDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ExoplanetDTO exoplanetDTO) {
        final Exoplanet exoplanet = new Exoplanet();
        mapToEntity(exoplanetDTO, exoplanet);
        return exoplanetRepository.save(exoplanet).getId();
    }

    public void update(final Long id, final ExoplanetDTO exoplanetDTO) {
        final Exoplanet exoplanet = exoplanetRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(exoplanetDTO, exoplanet);
        exoplanetRepository.save(exoplanet);
    }

    public void delete(final Long id) {
        final Exoplanet exoplanet = exoplanetRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        exoplanetRepository.delete(exoplanet);
    }

    private ExoplanetDTO mapToDTO(final Exoplanet exoplanet, final ExoplanetDTO exoplanetDTO) {
        exoplanetDTO.setId(exoplanet.getId());
        exoplanetDTO.setName(exoplanet.getName());
        exoplanetDTO.setMassEarth(exoplanet.getMassEarth());
        exoplanetDTO.setRadiusEarth(exoplanet.getRadiusEarth());
        exoplanetDTO.setOrbitalPeriodDays(exoplanet.getOrbitalPeriodDays());
        exoplanetDTO.setDiscoveryYear(exoplanet.getDiscoveryYear());
        exoplanetDTO.setIsInHabitableZone(exoplanet.getIsInHabitableZone());
        exoplanetDTO.setStar(exoplanet.getStar() == null ? null : exoplanet.getStar().getId());
        exoplanetDTO.setSpaceMissions(exoplanet.getSpaceMissions().stream()
                .map(SpaceMission::getId)
                .toList());
        return exoplanetDTO;
    }

    private Exoplanet mapToEntity(final ExoplanetDTO exoplanetDTO, final Exoplanet exoplanet) {
        exoplanet.setName(exoplanetDTO.getName());
        exoplanet.setMassEarth(exoplanetDTO.getMassEarth());
        exoplanet.setRadiusEarth(exoplanetDTO.getRadiusEarth());
        exoplanet.setOrbitalPeriodDays(exoplanetDTO.getOrbitalPeriodDays());
        exoplanet.setDiscoveryYear(exoplanetDTO.getDiscoveryYear());
        exoplanet.setIsInHabitableZone(exoplanetDTO.getIsInHabitableZone());
        final Star star = exoplanetDTO.getStar() == null ? null : starRepository.findById(exoplanetDTO.getStar())
                .orElseThrow(() -> new NotFoundException("star not found"));
        exoplanet.setStar(star);
        final List<SpaceMission> spaceMissions = spaceMissionRepository.findAllById(
                exoplanetDTO.getSpaceMissions() == null ? List.of() : exoplanetDTO.getSpaceMissions());
        if (spaceMissions.size() != (exoplanetDTO.getSpaceMissions() == null ? 0 : exoplanetDTO.getSpaceMissions().size())) {
            throw new NotFoundException("one of spaceMissions not found");
        }
        exoplanet.setSpaceMissions(new HashSet<>(spaceMissions));
        return exoplanet;
    }

    @EventListener(BeforeDeleteStar.class)
    public void on(final BeforeDeleteStar event) {
        final ReferencedException referencedException = new ReferencedException();
        final Exoplanet starExoplanet = exoplanetRepository.findFirstByStarId(event.getId());
        if (starExoplanet != null) {
            referencedException.setKey("star.exoplanet.star.referenced");
            referencedException.addParam(starExoplanet.getId());
            throw referencedException;
        }
    }

    @EventListener(BeforeDeleteSpaceMission.class)
    public void on(final BeforeDeleteSpaceMission event) {
        // remove many-to-many relations at owning side
        exoplanetRepository.findAllBySpaceMissionsId(event.getId()).forEach(exoplanet ->
                exoplanet.getSpaceMissions().removeIf(spaceMission -> spaceMission.getId().equals(event.getId())));
    }

}

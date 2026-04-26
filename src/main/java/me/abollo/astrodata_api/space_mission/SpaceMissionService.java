package me.abollo.astrodata_api.space_mission;

import java.util.List;
import me.abollo.astrodata_api.events.BeforeDeleteSpaceMission;
import me.abollo.astrodata_api.util.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class SpaceMissionService {

    private final SpaceMissionRepository spaceMissionRepository;
    private final ApplicationEventPublisher publisher;

    public SpaceMissionService(final SpaceMissionRepository spaceMissionRepository,
            final ApplicationEventPublisher publisher) {
        this.spaceMissionRepository = spaceMissionRepository;
        this.publisher = publisher;
    }

    public List<SpaceMissionDTO> findAll() {
        final List<SpaceMission> spaceMissions = spaceMissionRepository.findAll(Sort.by("id"));
        return spaceMissions.stream()
                .map(spaceMission -> mapToDTO(spaceMission, new SpaceMissionDTO()))
                .toList();
    }

    public SpaceMissionDTO get(final Long id) {
        return spaceMissionRepository.findById(id)
                .map(spaceMission -> mapToDTO(spaceMission, new SpaceMissionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SpaceMissionDTO spaceMissionDTO) {
        final SpaceMission spaceMission = new SpaceMission();
        mapToEntity(spaceMissionDTO, spaceMission);
        return spaceMissionRepository.save(spaceMission).getId();
    }

    public void update(final Long id, final SpaceMissionDTO spaceMissionDTO) {
        final SpaceMission spaceMission = spaceMissionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(spaceMissionDTO, spaceMission);
        spaceMissionRepository.save(spaceMission);
    }

    public void delete(final Long id) {
        final SpaceMission spaceMission = spaceMissionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeleteSpaceMission(id));
        spaceMissionRepository.delete(spaceMission);
    }

    private SpaceMissionDTO mapToDTO(final SpaceMission spaceMission,
            final SpaceMissionDTO spaceMissionDTO) {
        spaceMissionDTO.setId(spaceMission.getId());
        spaceMissionDTO.setName(spaceMission.getName());
        spaceMissionDTO.setAgency(spaceMission.getAgency());
        spaceMissionDTO.setLaunchDate(spaceMission.getLaunchDate());
        spaceMissionDTO.setStatus(spaceMission.getStatus());
        spaceMissionDTO.setDescription(spaceMission.getDescription());
        return spaceMissionDTO;
    }

    private SpaceMission mapToEntity(final SpaceMissionDTO spaceMissionDTO,
            final SpaceMission spaceMission) {
        spaceMission.setName(spaceMissionDTO.getName());
        spaceMission.setAgency(spaceMissionDTO.getAgency());
        spaceMission.setLaunchDate(spaceMissionDTO.getLaunchDate());
        spaceMission.setStatus(spaceMissionDTO.getStatus());
        spaceMission.setDescription(spaceMissionDTO.getDescription());
        return spaceMission;
    }

}

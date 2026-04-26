package me.abollo.astrodata_api.cosmic_event;

import java.util.List;
import me.abollo.astrodata_api.events.BeforeDeleteStar;
import me.abollo.astrodata_api.star.Star;
import me.abollo.astrodata_api.star.StarRepository;
import me.abollo.astrodata_api.util.NotFoundException;
import me.abollo.astrodata_api.util.ReferencedException;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CosmicEventService {

    private final CosmicEventRepository cosmicEventRepository;
    private final StarRepository starRepository;

    public CosmicEventService(final CosmicEventRepository cosmicEventRepository,
            final StarRepository starRepository) {
        this.cosmicEventRepository = cosmicEventRepository;
        this.starRepository = starRepository;
    }

    public List<CosmicEventDTO> findAll() {
        final List<CosmicEvent> cosmicEvents = cosmicEventRepository.findAll(Sort.by("id"));
        return cosmicEvents.stream()
                .map(cosmicEvent -> mapToDTO(cosmicEvent, new CosmicEventDTO()))
                .toList();
    }

    public CosmicEventDTO get(final Long id) {
        return cosmicEventRepository.findById(id)
                .map(cosmicEvent -> mapToDTO(cosmicEvent, new CosmicEventDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CosmicEventDTO cosmicEventDTO) {
        final CosmicEvent cosmicEvent = new CosmicEvent();
        mapToEntity(cosmicEventDTO, cosmicEvent);
        return cosmicEventRepository.save(cosmicEvent).getId();
    }

    public void update(final Long id, final CosmicEventDTO cosmicEventDTO) {
        final CosmicEvent cosmicEvent = cosmicEventRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cosmicEventDTO, cosmicEvent);
        cosmicEventRepository.save(cosmicEvent);
    }

    public void delete(final Long id) {
        final CosmicEvent cosmicEvent = cosmicEventRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        cosmicEventRepository.delete(cosmicEvent);
    }

    private CosmicEventDTO mapToDTO(final CosmicEvent cosmicEvent,
            final CosmicEventDTO cosmicEventDTO) {
        cosmicEventDTO.setId(cosmicEvent.getId());
        cosmicEventDTO.setName(cosmicEvent.getName());
        cosmicEventDTO.setEventType(cosmicEvent.getEventType());
        cosmicEventDTO.setEventDate(cosmicEvent.getEventDate());
        cosmicEventDTO.setMagnitude(cosmicEvent.getMagnitude());
        cosmicEventDTO.setDescription(cosmicEvent.getDescription());
        cosmicEventDTO.setStar(cosmicEvent.getStar() == null ? null : cosmicEvent.getStar().getId());
        return cosmicEventDTO;
    }

    private CosmicEvent mapToEntity(final CosmicEventDTO cosmicEventDTO,
            final CosmicEvent cosmicEvent) {
        cosmicEvent.setName(cosmicEventDTO.getName());
        cosmicEvent.setEventType(cosmicEventDTO.getEventType());
        cosmicEvent.setEventDate(cosmicEventDTO.getEventDate());
        cosmicEvent.setMagnitude(cosmicEventDTO.getMagnitude());
        cosmicEvent.setDescription(cosmicEventDTO.getDescription());
        final Star star = cosmicEventDTO.getStar() == null ? null : starRepository.findById(cosmicEventDTO.getStar())
                .orElseThrow(() -> new NotFoundException("star not found"));
        cosmicEvent.setStar(star);
        return cosmicEvent;
    }

    @EventListener(BeforeDeleteStar.class)
    public void on(final BeforeDeleteStar event) {
        final ReferencedException referencedException = new ReferencedException();
        final CosmicEvent starCosmicEvent = cosmicEventRepository.findFirstByStarId(event.getId());
        if (starCosmicEvent != null) {
            referencedException.setKey("star.cosmicEvent.star.referenced");
            referencedException.addParam(starCosmicEvent.getId());
            throw referencedException;
        }
    }

}

package COMP301Project.EventCatalogService.service;

import COMP301Project.EventCatalogService.dto.EventRequest;
import COMP301Project.EventCatalogService.entity.Event;
import COMP301Project.EventCatalogService.exception.EventNotFoundException;
import COMP301Project.EventCatalogService.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event createEvent(EventRequest request) {
        Event event = new Event(request.title(), request.description(), request.eventDate());
        event.setCreatorUserId(request.creatorId());
        return repository.save(event);
    }

    public Event updateEvent(Long id, EventRequest request) {
        return repository.findById(id).map(event -> {
            event.setTitle(request.title());
            event.setDescription(request.description());
            event.setEventDate(request.eventDate());
            return repository.save(event);
        }).orElseThrow(() -> new EventNotFoundException(id));
    }

    public List<Event> searchEventsByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}

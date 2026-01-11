package COMP301Project.EventCatalogService.controller;

import COMP301Project.EventCatalogService.dto.EventRequest;
import COMP301Project.EventCatalogService.entity.Event;
import COMP301Project.EventCatalogService.service.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@SecurityRequirement(name = "BasicAuth")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> all() {
        return eventService.getAllEvents();
    }

    @GetMapping("/search")
    public List<Event> search(@RequestParam String title) {
        return eventService.searchEventsByTitle(title);
    }

    @PostMapping
    public Event create(@RequestBody EventRequest request) {
        return eventService.createEvent(request);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody EventRequest request) {
        return eventService.updateEvent(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}

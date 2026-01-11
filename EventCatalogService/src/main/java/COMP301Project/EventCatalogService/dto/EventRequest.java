package COMP301Project.EventCatalogService.dto;

import java.time.LocalDateTime;

public record EventRequest(String title, String description, LocalDateTime eventDate, Long creatorId) {}

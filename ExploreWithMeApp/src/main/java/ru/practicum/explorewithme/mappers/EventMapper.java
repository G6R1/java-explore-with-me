package ru.practicum.explorewithme.mappers;

import ru.practicum.explorewithme.EventState;
import ru.practicum.explorewithme.Location;
import ru.practicum.explorewithme.dto.event.EventFullDto;
import ru.practicum.explorewithme.dto.event.EventShortDto;
import ru.practicum.explorewithme.dto.event.NewEventDto;
import ru.practicum.explorewithme.models.Category;
import ru.practicum.explorewithme.models.Event;
import ru.practicum.explorewithme.models.User;

import java.time.LocalDateTime;
import java.util.HashSet;

public class EventMapper {

    public static EventShortDto toEventShortDto(Event event) {
        if (event == null)
            return null;

        return new EventShortDto(event.getId(),
                event.getTitle(),
                event.getAnnotation(),
                CategoriesMapper.toCategoryDto(event.getCategory()),
                UserMapper.toUserShortDto(event.getInitiator()),
                event.getEventDate(),
                event.getPaid(),
                event.getConfirmedRequests(),
                event.getViews());
    }

    public static EventFullDto toEventFullDto(Event event) {
        if (event == null)
            return null;

        return new EventFullDto(event.getId(),
                event.getTitle(),
                event.getAnnotation(),
                event.getDescription(),
                CategoriesMapper.toCategoryDto(event.getCategory()),
                event.getCreatedOn(),
                event.getPublishedOn(),
                event.getEventDate(),
                UserMapper.toUserShortDto(event.getInitiator()),
                new Location(event.getLat(), event.getLon()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getRequestModeration(),
                event.getConfirmedRequests(),
                event.getState(),
                event.getViews());
    }

    public static Event toEvent(NewEventDto newEventDto, Category category, User initiator) {
        if (newEventDto == null)
            return null;

        return new Event(null,
                newEventDto.getTitle(),
                newEventDto.getAnnotation(),
                newEventDto.getDescription(),
                category,
                0,
                LocalDateTime.now(),
                null,
                newEventDto.getEventDate(),
                initiator,
                newEventDto.getLocation().getLat(),
                newEventDto.getLocation().getLon(),
                newEventDto.getPaid(),
                newEventDto.getParticipantLimit() == null ? 0 : newEventDto.getParticipantLimit(),
                newEventDto.getRequestModeration() == null || newEventDto.getRequestModeration(),
                EventState.PENDING,
                0,
                new HashSet<>());
    }
}

package ru.practicum.explorewithme.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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

@RequiredArgsConstructor
@Component
public class TestEvMap {

    private final CategoriesMapper categoriesMapper;
    private final UserMapper userMapper;

    public EventFullDto toEventFullDto(Event event) {
        if (event == null)
            return null;

        return EventFullDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .annotation(event.getAnnotation())
                .description(event.getDescription())
                .category(categoriesMapper.toCategoryDto(event.getCategory()))
                .createdOn(event.getCreatedOn())
                .publishedOn(event.getPublishedOn())
                .initiator(userMapper.toUserShortDto(event.getInitiator()))
                .location(new Location(event.getLat(), event.getLon()))
                .eventDate(event.getEventDate())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .requestModeration(event.getRequestModeration())
                .confirmedRequests(event.getConfirmedRequests())
                .state(event.getState())
                .views(event.getViews())
                .build();
    }

    public static Event toEvent(NewEventDto newEventDto) {
        if (newEventDto == null)
            return null;

        return Event.builder()
                .title(newEventDto.getTitle())
                .annotation(newEventDto.getAnnotation())
                .description(newEventDto.getDescription())
                .confirmedRequests(0)
                .createdOn(LocalDateTime.now())
                .eventDate(newEventDto.getEventDate())
                .lat(newEventDto.getLocation().getLat())
                .lon(newEventDto.getLocation().getLon())
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit() == null ? 0 : newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.getRequestModeration() == null || newEventDto.getRequestModeration())
                .state(EventState.PENDING)
                .views(0)
                .compilations(new HashSet<>())
                .build();
    }
}

package ru.practicum.explorewithme.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
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

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoriesMapper.class})
public interface EventMapper {

    EventShortDto toEventShortDto(Event event);


    EventFullDto toEventFullDto(Event event);

    @Mapping(target = "lat", source = "newEventDto.location.lat")
    @Mapping(target = "lon", source = "newEventDto.location.lon")
    Event toEvent(NewEventDto newEventDto, Category category, User initiator);
    //{
//        if (newEventDto == null)
//            return null;
//
//        return Event.builder()
//                .title(newEventDto.getTitle())
//                .annotation(newEventDto.getAnnotation())
//                .description(newEventDto.getDescription())
//                .category(category)
//                .confirmedRequests(0)
//                .createdOn(LocalDateTime.now())
//                .eventDate(newEventDto.getEventDate())
//                .initiator(initiator)
//                .lat(newEventDto.getLocation().getLat())
//                .lon(newEventDto.getLocation().getLon())
//                .paid(newEventDto.getPaid())
//                .participantLimit(newEventDto.getParticipantLimit() == null ? 0 : newEventDto.getParticipantLimit())
//                .requestModeration(newEventDto.getRequestModeration() == null || newEventDto.getRequestModeration())
//                .state(EventState.PENDING)
//                .views(0)
//                .compilations(new HashSet<>())
//                .build();
//    }
}

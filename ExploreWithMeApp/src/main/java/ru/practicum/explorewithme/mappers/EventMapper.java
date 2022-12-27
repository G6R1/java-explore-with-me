package ru.practicum.explorewithme.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.explorewithme.Location;
import ru.practicum.explorewithme.dto.event.EventFullDto;
import ru.practicum.explorewithme.dto.event.EventShortDto;
import ru.practicum.explorewithme.dto.event.NewEventDto;
import ru.practicum.explorewithme.models.Event;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoriesMapper.class})
public interface EventMapper {

    //норм
    EventShortDto toEventShortDto(Event event);

    @Mapping(target = "location", expression = "java(getLocation(event.getLat(), event.getLon()))")
    EventFullDto toEventFullDto(Event event);

    default Location getLocation(Double lat, Double lon) {
        return new Location(lat, lon);
    }

    @Mapping(target = "lat", source = "newEventDto.location.lat")
    @Mapping(target = "lon", source = "newEventDto.location.lon")
    @Mapping(target = "confirmedRequests", constant = "0")
    @Mapping(target = "createdOn", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "participantLimit", source= "participantLimit", defaultValue = "0")
    @Mapping(target = "requestModeration", source= "requestModeration", defaultValue = "true")
    @Mapping(target = "state", constant= "EventState.PENDING")
    @Mapping(target = "views", constant = "0")
    @Mapping(target = "compilations", expression = "java(new java.util.HashSet<>())")
    Event toEvent(NewEventDto newEventDto);
}

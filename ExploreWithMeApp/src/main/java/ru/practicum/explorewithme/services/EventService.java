package ru.practicum.explorewithme.services;

import ru.practicum.explorewithme.EventState;
import ru.practicum.explorewithme.EventsSortType;
import ru.practicum.explorewithme.dto.event.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface EventService {
    List<EventShortDto> getEvents(String text,
                                  Set<Long> categories,
                                  Boolean paid,
                                  Boolean onlyAvailable,
                                  EventsSortType sort,
                                  LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd,
                                  Integer from,
                                  Integer size,
                                  HttpServletRequest request);

    List<EventFullDto> getEventsAdmin(Set<Long> users,
                                      Set<EventState> states,
                                      Set<Integer> categories,
                                      LocalDateTime rangeStart,
                                      LocalDateTime rangeEnd,
                                      Integer from,
                                      Integer size);


    EventFullDto getEvent(Long eventId, HttpServletRequest request);

    EventFullDto getMyEvent(Long userId, Long eventId);

    List<EventShortDto> getMyEvents(Long userId, Integer from, Integer size);

    EventFullDto createEvent(NewEventDto newEventDto, Long userId);

    EventFullDto patchEvent(UpdateEventRequest updateEventRequest);

    EventFullDto adminUpdateEvent(AdminUpdateEventRequest updateEventRequest, Long eventId);

    EventFullDto cancelEvent(Long userId, Long eventId);

    EventFullDto publishEvent(Long eventId);

    EventFullDto rejectEvent(Long eventId);

    /*
    Увеличивает или умееньшает значение подтвержденных заявок на участие в событии на 1
     */
    void changeConfirmRequests(boolean increase, Long eventId);
}

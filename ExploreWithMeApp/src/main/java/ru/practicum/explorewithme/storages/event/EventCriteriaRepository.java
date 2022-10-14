package ru.practicum.explorewithme.storages.event;

import ru.practicum.explorewithme.EventState;
import ru.practicum.explorewithme.EventsSortType;
import ru.practicum.explorewithme.models.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface EventCriteriaRepository {

    List<Event> publicSearchByParameters(String text,
                                         Set<Long> categories,
                                         Boolean paid,
                                         Boolean onlyAvailable,
                                         EventsSortType sort,
                                         LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd,
                                         Integer from,
                                         Integer size);

    List<Event> adminSearchByParameters(Set<Long> users,
                                        Set<EventState> states,
                                        Set<Integer> categories,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd,
                                        Integer from,
                                        Integer size);

    List<Event> findSubscriptionsEvents(List<Long> subsId,
                                        EventsSortType sort,
                                        Integer from,
                                        Integer size);
}

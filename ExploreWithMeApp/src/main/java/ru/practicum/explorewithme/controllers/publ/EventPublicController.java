package ru.practicum.explorewithme.controllers.publ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.EventsSortType;
import ru.practicum.explorewithme.dto.event.EventFullDto;
import ru.practicum.explorewithme.dto.event.EventShortDto;
import ru.practicum.explorewithme.services.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
public class EventPublicController {
    private final EventService eventService;

    public EventPublicController(EventService eventService) {
        this.eventService = eventService;
    }

    /*
    Получение событий с возможностью фильтрации
     */
    @GetMapping("/events")
    public List<EventShortDto> getEvents(@RequestParam(required = false) String text,
                                         @RequestParam(required = false) Set<Long> categories,
                                         @RequestParam(required = false) Boolean paid,
                                         @RequestParam(required = false, defaultValue = "false") Boolean onlyAvailable,
                                         @RequestParam(required = false) EventsSortType sort,
                                         @RequestParam(required = false) LocalDateTime rangeStart,
                                         @RequestParam(required = false) LocalDateTime rangeEnd,
                                         @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                         @RequestParam(required = false, defaultValue = "10") @Positive Integer size,
                                         HttpServletRequest request) {

        List<EventShortDto> dtoList = eventService.getEvents(text,
                categories,
                paid,
                onlyAvailable,
                sort,
                rangeStart,
                rangeEnd,
                from,
                size,
                request);
        log.info("Выполнен запрос getEvents");
        return dtoList;
    }

    /*
    Получение подробной информации об опубликованном событии по его идентификатору
     */
    @GetMapping("/events/{id}")
    public EventFullDto getEvent(@PathVariable Long id, HttpServletRequest request) {
        EventFullDto foundEvent = eventService.getEvent(id, request);

        log.info("Выполнен запрос getEvent");
        return foundEvent;
    }
}
package ru.practicum.explorewithme.controllers.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.dto.compilation.NewCompilationDto;
import ru.practicum.explorewithme.services.CompilationService;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/admin/compilations")
public class CompilationsAdminController {

    private final CompilationService compilationService;

    public CompilationsAdminController(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    /*
    Создать подборку событий
     */
    @PostMapping()
    public CompilationDto createCompilation(@Valid @RequestBody NewCompilationDto newCompilationDto) {

        CompilationDto compilationDto = compilationService.createCompilation(newCompilationDto);
        log.info("Выполнен запрос createCompilation");
        return compilationDto;
    }

    /*
    Удаление подборки
     */
    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable Long compId) {

        compilationService.deleteCompilation(compId);
        log.info("Выполнен запрос deleteCompilation");
    }

    /*
    Удаление события из подборки
     */
    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable Long compId,
                                           @PathVariable Long eventId) {

        compilationService.deleteEventFromCompilation(compId, eventId);
        log.info("Выполнен запрос deleteEventFromCompilation");
    }

    /*
    Добавить событие в подборку
     */
    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(@PathVariable Long compId,
                                      @PathVariable Long eventId) {

        compilationService.addEventToCompilation(compId, eventId);
        log.info("Выполнен запрос addEventFromCompilation");
    }

    @DeleteMapping("/{compId}/pin")
    public void unpinCompilation(@PathVariable Long compId) {

        compilationService.unpinCompilation(compId);
        log.info("Выполнен запрос deleteCompilation");
    }

    @PatchMapping("/{compId}/pin")
    public void pinCompilation(@PathVariable Long compId) {

        compilationService.pinCompilation(compId);
        log.info("Выполнен запрос deleteCompilation");
    }


}

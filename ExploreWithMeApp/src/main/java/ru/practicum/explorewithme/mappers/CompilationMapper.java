package ru.practicum.explorewithme.mappers;

import ru.practicum.explorewithme.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.models.Compilation;

import java.util.stream.Collectors;

public class CompilationMapper {

    public static CompilationDto toCompilationDto(Compilation compilation) {
        if (compilation == null)
            return null;

        return new CompilationDto(compilation.getId(),
                compilation.getPinned(),
                compilation.getTitle(),
                compilation.getEvents().stream().map(EventMapper::toEventShortDto).collect(Collectors.toList()));
    }
}

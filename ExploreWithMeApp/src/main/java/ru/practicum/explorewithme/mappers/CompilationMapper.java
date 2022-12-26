package ru.practicum.explorewithme.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.explorewithme.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.models.Compilation;

import java.util.stream.Collectors;

//@Mapper(uses = {PatientMapper.class})
public class CompilationMapper {

    private static final EventMapper eventMapper = Mappers.getMapper(EventMapper.class);

    public static CompilationDto toCompilationDto(Compilation compilation) {
        if (compilation == null)
            return null;

        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(compilation.getEvents().stream()
                        .map(eventMapper::toEventShortDto)
                        .collect(Collectors.toList()))
                .build();
    }
}

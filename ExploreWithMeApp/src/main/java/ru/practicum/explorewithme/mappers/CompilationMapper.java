package ru.practicum.explorewithme.mappers;

import org.mapstruct.Mapper;
import ru.practicum.explorewithme.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.models.Compilation;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface CompilationMapper {

    CompilationDto toCompilationDto(Compilation compilation) ;
}

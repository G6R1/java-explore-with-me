package ru.practicum.explorewithme.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.explorewithme.dto.user.UserDto;
import ru.practicum.explorewithme.dto.user.UserShortDto;
import ru.practicum.explorewithme.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserShortDto toUserShortDto(User user);

    UserDto toUserDto(User user);
}

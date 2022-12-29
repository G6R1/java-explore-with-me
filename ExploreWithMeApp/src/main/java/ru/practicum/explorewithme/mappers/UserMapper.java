package ru.practicum.explorewithme.mappers;

import org.mapstruct.Mapper;
import ru.practicum.explorewithme.dto.user.UserDto;
import ru.practicum.explorewithme.dto.user.UserShortDto;
import ru.practicum.explorewithme.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserShortDto toUserShortDto(User user);

    UserDto toUserDto(User user);
}

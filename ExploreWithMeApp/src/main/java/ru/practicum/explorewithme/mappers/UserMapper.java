package ru.practicum.explorewithme.mappers;

import ru.practicum.explorewithme.dto.user.UserDto;
import ru.practicum.explorewithme.dto.user.UserShortDto;
import ru.practicum.explorewithme.models.User;

public class UserMapper {

    public static UserShortDto toUserShortDto(User user) {
        if (user == null)
            return null;

        return new UserShortDto(user.getId(), user.getName());
    }

    public static UserDto toUserDto(User user) {
        if (user == null)
            return null;

        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}

package ru.practicum.explorewithme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Пользователь (краткая информация)
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserShortDto {
    @NotNull
    Long id;
    @NotBlank
    String name;
}

package ru.practicum.explorewithme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Данные для создания нового пользователя
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewUserRequest {
    @NotBlank
    String name;
    @NotNull
    @Email
    String email;
}

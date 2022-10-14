package ru.practicum.explorewithme.dto.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Данные для добавления новой категории
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewCategoryDto {
    @NotBlank
    String name;
}

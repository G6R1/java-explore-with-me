package ru.practicum.explorewithme.dto.categories;

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
    private String name;
}

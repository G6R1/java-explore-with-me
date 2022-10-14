package ru.practicum.explorewithme.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * Данные для добавления новой подборки событий
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewCompilationDto {

    @NotBlank
    String title;
    Boolean pinned = false;
    Set<Long> events = new HashSet<>();
}

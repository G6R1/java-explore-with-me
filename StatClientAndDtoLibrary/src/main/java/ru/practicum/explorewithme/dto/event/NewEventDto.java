package ru.practicum.explorewithme.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.Location;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Данные для добавления нового события.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewEventDto {
    @NotBlank
    @Size(min = 3, max = 120)
    String title;
    @NotBlank
    @Size(min = 20, max = 2000)
    String annotation;
    @Size(min = 20, max = 7000)
    String description;
    @NotNull
    Long category;
    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @NotNull
    Location location;
    @NotNull
    Boolean paid;
    Integer participantLimit = 0;
    Boolean requestModeration = true;
}

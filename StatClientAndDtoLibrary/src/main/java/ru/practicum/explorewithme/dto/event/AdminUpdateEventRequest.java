package ru.practicum.explorewithme.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.Location;

import java.time.LocalDateTime;

/**
 * Информация для редактирования события администратором. Все поля необязательные. Значение полей не валидируется.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminUpdateEventRequest {
    String title;
    String annotation;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Long category;
    Location location;
    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
}

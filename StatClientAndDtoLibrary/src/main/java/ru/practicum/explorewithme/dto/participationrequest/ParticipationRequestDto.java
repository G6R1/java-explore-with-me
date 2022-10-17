package ru.practicum.explorewithme.dto.participationrequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.ParticipationStatus;

import java.time.LocalDateTime;

/**
 * Заявка на участие в событии
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParticipationRequestDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private Long event;
    private Long requester;
    private ParticipationStatus status;
}

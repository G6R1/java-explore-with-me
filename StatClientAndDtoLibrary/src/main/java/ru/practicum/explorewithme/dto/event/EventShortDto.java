package ru.practicum.explorewithme.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.dto.user.UserShortDto;
import ru.practicum.explorewithme.dto.categories.CategoryDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventShortDto {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String annotation;
    @NotNull
    private CategoryDto category;
    @NotNull
    private UserShortDto initiator;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    @NotNull
    private Boolean paid;
    private Integer confirmedRequests;
    private Integer views;
}

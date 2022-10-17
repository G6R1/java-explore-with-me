package ru.practicum.explorewithme.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewStats {
    private String app;
    private String uri;
    private Integer hits;
}

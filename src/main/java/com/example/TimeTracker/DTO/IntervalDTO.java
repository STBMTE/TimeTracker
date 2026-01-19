package com.example.TimeTracker.DTO;

import com.example.TimeTracker.Model.ActivityType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class IntervalDTO {
    @NotNull(message = "Время начала обязательно")
    @Min(value = 0, message = "start не может быть меньше 0")
    @Max(value = 86400, message = "start не может быть больше 86400")
    private int start;

    @NotNull(message = "Время конца обязательно")
    @Min(value = 0, message = "startSec не может быть меньше 0")
    @Max(value = 86400, message = "startSec не может быть больше 86400")
    private int end;

    @NotNull(message = "Тип активности обязателен")
    private ActivityType type;

    public int getEnd() {
        return end;
    }

    public int getStart(){
        return start;
    }

    public ActivityType getType(){
        return type;
    }
}

package br.com.brisabr.helpdesk.model.sla.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record SlaResponseDTO(
    Long id,
    String name,
    String description,
    Long responseTime,
    Long resolutionTime,
    Boolean isActive,
    SlaPriorityResponseDTO priority,
    SlaCalendarResponseDTO calendar,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public record SlaPriorityResponseDTO(
        Long id,
        String name,
        String description,
        Integer level,
        String color
    ) {}
    
    public record SlaCalendarResponseDTO(
        Long id,
        String name,
        String description,
        String timezone,
        Boolean considerWeekends,
        Boolean considerHolidays,
        List<SlaDayResponseDTO> slaDays
    ) {}
    
    public record SlaDayResponseDTO(
        Long id,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        Boolean isWorkingDay
    ) {}
}
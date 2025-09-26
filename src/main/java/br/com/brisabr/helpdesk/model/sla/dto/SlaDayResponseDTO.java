package br.com.brisabr.helpdesk.model.sla.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record SlaDayResponseDTO(
    Long id,
    DayOfWeek dayOfWeek,
    LocalTime startTime,
    LocalTime endTime,
    Boolean isWorkingDay
) {}

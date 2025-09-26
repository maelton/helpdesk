package br.com.brisabr.helpdesk.model.sla.dto;

import java.util.List;

public record SlaCalendarResponseDTO(
    Long id,
    String name,
    String description,
    String timezone,
    Boolean considerWeekends,
    Boolean considerHolidays,
    List<SlaDayResponseDTO> slaDays
) {}

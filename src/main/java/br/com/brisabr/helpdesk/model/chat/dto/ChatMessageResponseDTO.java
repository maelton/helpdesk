package br.com.brisabr.helpdesk.model.chat.dto;

public record ChatMessageResponseDTO(
    Long id,
    Long chatId,
    Long senderID,
    String content
) {}

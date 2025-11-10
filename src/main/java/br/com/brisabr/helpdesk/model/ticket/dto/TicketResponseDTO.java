package br.com.brisabr.helpdesk.model.ticket.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import br.com.brisabr.helpdesk.model.chat.enums.ChatMessageType;
import br.com.brisabr.helpdesk.model.user.UserRole;

public record TicketResponseDTO(
    Long id,
    String title,
    Long slaId,
    Long requesterId,
    Long responsibleEmployeeId,
    Long productId,
    String description,
    String priority,
    LocalDateTime dueDate,
    String status,
    LocalDateTime closedAt,
    Long closedById,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Chat chat
) {
    public record Chat (
        List<ChatMessage> messages
    ) {}

    public record ChatMessage (
        Long id,
        Sender sender,
        String content,
        ChatMessageType type,
        LocalDateTime timestamp
    ) {}

    public record Sender (
        Long id,
        String username,
        Set<UserRole> roles
    ) {}
}
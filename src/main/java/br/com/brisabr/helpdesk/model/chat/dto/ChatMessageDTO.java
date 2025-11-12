package br.com.brisabr.helpdesk.model.chat.dto;

import java.time.LocalDateTime;

import br.com.brisabr.helpdesk.model.chat.ChatMessage;
import br.com.brisabr.helpdesk.model.chat.enums.ChatMessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private ChatMessageType type;
    private SenderDTO sender;
    
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SenderDTO {
        private Long id;
        private String username;
    }
    
    /**
     * Converte uma entidade ChatMessage para DTO
     */
    public static ChatMessageDTO fromEntity(ChatMessage chatMessage) {
        if (chatMessage == null) {
            return null;
        }
        
        SenderDTO senderDTO = null;
        if (chatMessage.getSender() != null) {
            senderDTO = new SenderDTO(
                chatMessage.getSender().getId(),
                chatMessage.getSender().getUsername()
            );
        }
        
        return new ChatMessageDTO(
            chatMessage.getId(),
            chatMessage.getContent(),
            chatMessage.getCreatedAt(),
            chatMessage.getType(),
            senderDTO
        );
    }
}

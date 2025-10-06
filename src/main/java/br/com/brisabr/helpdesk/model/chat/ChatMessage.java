package br.com.brisabr.helpdesk.model.chat;

import java.time.LocalDateTime;

import br.com.brisabr.helpdesk.model.chat.enums.ChatMessageType;
import br.com.brisabr.helpdesk.model.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_chat_message")
@Getter @Setter
@NoArgsConstructor
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="chat_id")
    Chat chat;
    
    @Enumerated(EnumType.STRING)
    ChatMessageType type;
    
    @ManyToOne
    User sender;
    
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
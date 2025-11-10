package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}

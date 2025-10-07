package br.com.brisabr.helpdesk.controller;

import br.com.brisabr.helpdesk.model.chat.ChatMessage;
import br.com.brisabr.helpdesk.model.chat.enums.ChatMessageType;
import br.com.brisabr.helpdesk.model.ticket.Ticket;
import br.com.brisabr.helpdesk.model.user.User;
import br.com.brisabr.helpdesk.repository.TicketRepository;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {
    
    private final TicketRepository ticketRepository;
    
    public ChatController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    
    /**
     * Endpoint WebSocket para enviar mensagens no chat de um ticket específico.
     * 
     * Cliente deve enviar mensagem para: /app/chat/{ticketId}
     * Cliente deve se inscrever em: /topic/chat/{ticketId}
     * 
     * @param ticketId ID do ticket
     * @param message Conteúdo da mensagem
     * @param headerAccessor Acesso aos headers da mensagem WebSocket
     * @return ChatMessage processada
     */
    @MessageMapping("/chat/{ticketId}")
    @SendTo("/topic/chat/{ticketId}")
    public ChatMessage sendMessage(
            @DestinationVariable Long ticketId,
            String message,
            SimpMessageHeaderAccessor headerAccessor) {
        
        // Obter autenticação do header
        Authentication authentication = (Authentication) headerAccessor.getUser();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Usuário não autenticado");
        }
        
        User user = (User) authentication.getPrincipal();
        
        // Verificar se o ticket existe
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElse(null);
        
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket não encontrado");
        }
        
        // Verificar autorização: usuário deve ser o requester ou o agent do ticket
        boolean isRequester = ticket.getRequester() != null && 
                             user.getUsername().equals(ticket.getRequester().getUsername());
        boolean isAgent = ticket.getAssignedTo() != null && 
                         user.getUsername().equals(ticket.getAssignedTo().getUsername());
        
        if (!isRequester && !isAgent) {
            throw new SecurityException("Usuário não autorizado a enviar mensagens neste chat");
        }
        
        // Criar mensagem do chat
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message);
        chatMessage.setSender(user);
        chatMessage.setCreatedAt(LocalDateTime.now());
        
        // Determinar tipo da mensagem baseado no tipo do usuário
        if (isRequester) {
            chatMessage.setType(ChatMessageType.CLIENT_MESSAGE);
        } else {
            chatMessage.setType(ChatMessageType.AGENT_MESSAGE);
        }
        
        return chatMessage;
    }
}

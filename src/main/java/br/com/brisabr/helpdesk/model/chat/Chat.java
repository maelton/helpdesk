package br.com.brisabr.helpdesk.model.chat;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.brisabr.helpdesk.model.ticket.Ticket;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_chat")
@Setter @Getter
@NoArgsConstructor
public class Chat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    private Ticket ticket;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat")
    Set<ChatMessage> messages = new LinkedHashSet<>();
}

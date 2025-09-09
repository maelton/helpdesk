package br.com.brisabr.helpdesk.model.ticket;

import br.com.brisabr.helpdesk.model.product.Product;
import br.com.brisabr.helpdesk.model.sla.Sla;
import br.com.brisabr.helpdesk.model.ticket.category.TicketCategory;
import br.com.brisabr.helpdesk.model.user.client.Client;
import br.com.brisabr.helpdesk.model.user.employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_ticket")
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Sla sla;
    @ManyToOne
    private Client requester;
    @ManyToOne
    private Product product;
    
    @ManyToOne
    private Employee closedBy;
    private String description;
    private String priority;
    private LocalDateTime dueDate;
    private String status;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private TicketCategory category;
    private LocalDateTime closedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //TODO: private Contract contract;
}
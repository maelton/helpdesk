package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.product.Product;
import br.com.brisabr.helpdesk.model.sla.Sla;
import br.com.brisabr.helpdesk.model.ticket.Ticket;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketClosedDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketCreateDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketOpeningDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketResponseDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketUpdateDTO;
import br.com.brisabr.helpdesk.model.user.client.Client;
import br.com.brisabr.helpdesk.model.user.employee.Employee;
import br.com.brisabr.helpdesk.repository.ClientRepository;
import br.com.brisabr.helpdesk.repository.EmployeeRepository;
import br.com.brisabr.helpdesk.repository.ProductRepository;
import br.com.brisabr.helpdesk.repository.SlaRepository;
import br.com.brisabr.helpdesk.repository.TicketRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SlaRepository slaRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public TicketResponseDTO create(TicketCreateDTO dto) {
        Ticket ticket = toEntity(dto);
        Ticket saved = ticketRepository.save(ticket);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public TicketResponseDTO findById(Long id) {
        Ticket ticket = getTicket(id);
        return toResponse(ticket);
    }

    @Transactional(readOnly = true)
    public Page<TicketResponseDTO> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional
    public TicketResponseDTO update(Long id, TicketUpdateDTO dto) {
        Ticket ticket = getTicket(id);

        if (dto.title() != null) ticket.setTitle(dto.title());
        if (dto.description() != null) ticket.setDescription(dto.description());
        if (dto.priority() != null) ticket.setPriority(dto.priority());
        if (dto.dueDate() != null) ticket.setDueDate(dto.dueDate());
        if (dto.status() != null) ticket.setStatus(dto.status());

        if (dto.slaId() != null) {
            Sla sla = slaRepository.findById(dto.slaId())
                .orElseThrow(() -> new EntityNotFoundException("SLA not found: " + dto.slaId()));
            ticket.setSla(sla);
        }

        if (dto.requesterId() != null) {
            Client client = clientRepository.findById(dto.requesterId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found: " + dto.requesterId()));
            ticket.setRequester(client);
        }

        if (dto.productId() != null) {
            Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + dto.productId()));
            ticket.setProduct(product);
        }

        if (dto.closedById() != null) {
            Employee closer = employeeRepository.findById(dto.closedById())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + dto.closedById()));
            ticket.setClosedBy(closer);
        }
        if (dto.closedAt() != null) {
            ticket.setClosedAt(dto.closedAt());
        }

        Ticket saved = ticketRepository.save(ticket);
        return toResponse(saved);
    }

    @Transactional
    public TicketResponseDTO close(Long id, TicketClosedDTO dto) {
        Ticket ticket = getTicket(id);

        Employee closer = employeeRepository.findById(dto.closedById())
            .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + dto.closedById()));

        ticket.setClosedBy(closer);
        ticket.setClosedAt(dto.closedAt() != null ? dto.closedAt() : LocalDateTime.now());
        if (dto.status() != null) {
            ticket.setStatus(dto.status());
        }

        Ticket saved = ticketRepository.save(ticket);
        return toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new EntityNotFoundException("Ticket not found: " + id);
        }
        ticketRepository.deleteById(id);
    }

    private Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ticket not found: " + id));
    }

    private Ticket toEntity(TicketCreateDTO dto) {
        Sla sla = slaRepository.findById(dto.slaId())
            .orElseThrow(() -> new EntityNotFoundException("SLA not found: " + dto.slaId()));
        Client requester = clientRepository.findById(dto.requesterId())
            .orElseThrow(() -> new EntityNotFoundException("Client not found: " + dto.requesterId()));
        Product product = productRepository.findById(dto.productId())
            .orElseThrow(() -> new EntityNotFoundException("Product not found: " + dto.productId()));

        Ticket t = new Ticket();
        t.setTitle(dto.title());
        t.setSla(sla);
        t.setRequester(requester);
        t.setProduct(product);
        t.setDescription(dto.description());
        t.setPriority(dto.priority());
        t.setDueDate(dto.dueDate());
        t.setStatus(dto.status());
        return t;
    }

    private Ticket toEntity(TicketOpeningDTO dto) {
        Product product = productRepository.findById(dto.productId())
            .orElseThrow(() -> new EntityNotFoundException("Product not found: " + dto.productId()));

        Ticket ticket = new Ticket();
            ticket.setTitle(dto.title());
            ticket.setProduct(product);
            ticket.setDescription(dto.description());
            ticket.setSla(product.getSla());
        return ticket;
    }

    @Transactional
    public TicketResponseDTO openTicket(TicketOpeningDTO dto, String requesterUsername) {
        Ticket ticket = toEntity(dto);

        Long responsibleId = employeeRepository.findRandomEmployeeId()
            .orElseThrow(() -> new RuntimeException("Unable to find employee ID"));
        Employee responsibleEmployee = employeeRepository.findById(responsibleId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + responsibleId));
        Client requester = clientRepository.findByUsername(requesterUsername)
            .orElseThrow(() -> new EntityNotFoundException("Unable to find client"));

        ticket.setAssignedTo(responsibleEmployee);
        ticket.setRequester(requester);

        Ticket saved = ticketRepository.save(ticket);
        return toResponse(saved);
    } 

    private TicketResponseDTO toResponse(Ticket t) {
        return new TicketResponseDTO(
            t.getId(),
            t.getTitle(),
            t.getSla() != null ? t.getSla().getId() : null,
            t.getRequester() != null ? t.getRequester().getId() : null,
            t.getAssignedTo() != null ? t.getAssignedTo().getId() : null,
            t.getProduct() != null ? t.getProduct().getId() : null,
            t.getClosedBy() != null ? t.getClosedBy().getId() : null,
            t.getDescription(),
            t.getPriority(),
            t.getDueDate(),
            t.getStatus(),
            t.getClosedAt(),
            t.getCreatedAt(),
            t.getUpdatedAt()
        );
    }
}
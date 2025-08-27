package br.com.brisabr.helpdesk.model.user.employee;

import br.com.brisabr.helpdesk.utils.mail.Email;
import br.com.brisabr.helpdesk.utils.mail.EmailService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class EmployeeCreatedListener {

    private final EmailService emailService;

    public EmployeeCreatedListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEmployeeCreated(EmployeeCreatedEvent event) {
        Employee employee = event.getEmployee();
        emailService.sendEmail(
            new Email(
                employee.getUsername(),
                "User Created",
                "Your password reset URL here"
            )
        );
    }
}


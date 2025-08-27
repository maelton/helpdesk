package br.com.brisabr.helpdesk.utils.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender sender;
    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    @Async
    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.to());
            message.setSubject(email.subject());
            message.setText(email.subject());
        sender.send(message);
    }
}

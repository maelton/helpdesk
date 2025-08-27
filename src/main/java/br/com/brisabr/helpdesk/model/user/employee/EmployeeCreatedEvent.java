package br.com.brisabr.helpdesk.model.user.employee;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class EmployeeCreatedEvent {
    private final Employee employee;
}

ALTER TABLE tb_ticket
    ADD COLUMN assigned_to_id BIGINT;

ALTER TABLE tb_ticket
    ADD CONSTRAINT FK_TB_TICKET_ON_ASSIGNED_TO
    FOREIGN KEY (assigned_to_id) REFERENCES tb_employee(id);
CREATE TABLE IF NOT EXISTS tb_client
(
    id      BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name    VARCHAR(255),
    cpf     VARCHAR(11) NOT NULL,
    address VARCHAR(255),
    phone   VARCHAR(255),
    status  BOOLEAN
);

CREATE TABLE IF NOT EXISTS tb_department
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS tb_employee
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    internal_code UUID,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    cpf           VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_product
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    internal_code UUID,
    name          VARCHAR(255),
    is_active     BOOLEAN,
    description   VARCHAR(255),
    is_physical   BOOLEAN,
    category_id   BIGINT,
    sla_id        BIGINT,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS tb_product_category
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(255),
    description VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS tb_sla
(
    id              BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name            VARCHAR(255),
    description     VARCHAR(255),
    response_time   BIGINT,
    resolution_time BIGINT,
    is_active       BOOLEAN,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS tb_ticket
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title        VARCHAR(255),
    sla_id       BIGINT,
    requester_id BIGINT,
    product_id   BIGINT,
    closed_by_id BIGINT,
    description  VARCHAR(255),
    priority     VARCHAR(255),
    due_date     TIMESTAMP WITHOUT TIME ZONE,
    status       VARCHAR(255),
    category_id  BIGINT,
    closed_at    TIMESTAMP WITHOUT TIME ZONE,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS tb_ticket_category
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title       VARCHAR(255),
    description VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS tb_user
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username            VARCHAR(255),
    password            VARCHAR(255)                            NOT NULL,
    is_password_changed BOOLEAN,
    is_active           BOOLEAN,
    created_at          TIMESTAMP WITHOUT TIME ZONE,
    updated_at          TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS tb_user_role
(
    id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tb_user_roles
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    UNIQUE(user_id, roles_id)
);

ALTER TABLE tb_client
    ADD CONSTRAINT uc_tb_client_cpf UNIQUE (cpf);

ALTER TABLE tb_employee
    ADD CONSTRAINT uc_tb_employee_cpf UNIQUE (cpf);

ALTER TABLE tb_user_role
    ADD CONSTRAINT uc_tb_user_role_name UNIQUE (name);

ALTER TABLE tb_user
    ADD CONSTRAINT uc_tb_user_username UNIQUE (username);

ALTER TABLE tb_client
    ADD CONSTRAINT FK_TB_CLIENT_ON_ID FOREIGN KEY (id) REFERENCES tb_user (id);

ALTER TABLE tb_employee
    ADD CONSTRAINT FK_TB_EMPLOYEE_ON_ID FOREIGN KEY (id) REFERENCES tb_user (id);

ALTER TABLE tb_product
    ADD CONSTRAINT FK_TB_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES tb_product_category (id);

ALTER TABLE tb_product
    ADD CONSTRAINT FK_TB_PRODUCT_ON_SLA FOREIGN KEY (sla_id) REFERENCES tb_sla (id);

ALTER TABLE tb_ticket
    ADD CONSTRAINT FK_TB_TICKET_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES tb_ticket_category (id);

ALTER TABLE tb_ticket
    ADD CONSTRAINT FK_TB_TICKET_ON_CLOSEDBY FOREIGN KEY (closed_by_id) REFERENCES tb_employee (id);

ALTER TABLE tb_ticket
    ADD CONSTRAINT FK_TB_TICKET_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES tb_product (id);

ALTER TABLE tb_ticket
    ADD CONSTRAINT FK_TB_TICKET_ON_REQUESTER FOREIGN KEY (requester_id) REFERENCES tb_client (id);

ALTER TABLE tb_ticket
    ADD CONSTRAINT FK_TB_TICKET_ON_SLA FOREIGN KEY (sla_id) REFERENCES tb_sla (id);

ALTER TABLE tb_user_roles
    ADD CONSTRAINT fk_tbuserol_on_user FOREIGN KEY (user_id) REFERENCES tb_user (id);

ALTER TABLE tb_user_roles
    ADD CONSTRAINT fk_tbuserol_on_user_role FOREIGN KEY (roles_id) REFERENCES tb_user_role (id);
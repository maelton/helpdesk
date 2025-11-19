-- Insert TECHNICIAN role
INSERT INTO tb_user_role (name) VALUES ('TECHNICIAN');

-- Insert new users
INSERT INTO tb_user (username, password, is_password_changed, is_active, created_at, updated_at)
VALUES
    (
        'cliente@brisa.com.br',
        '{bcrypt}$2a$12$iUqrmhww3IQqlP5t3Q1tU.3VIWtr2YpuavKrxDClBex0fQjTcHqGC', -- cliente
        true,
        true,
        now(),
        now()
    ),
    (
        'tecnico@brisa.com.br',
        '{bcrypt}$2a$12$dU5TR/yc5rA5.qv1IVppZu0TyMMs9d9IYcybx9gGuHwOUBgCnNOaC', -- tecnico
        true,
        true,
        now(),
        now()
    );

-- Insert new client
INSERT INTO tb_client (id, name, tax_id, address, phone, status)
SELECT id, 'Novo Cliente', '12345678901', 'Rua Nova', '11 88888-8888', true
FROM tb_user WHERE username = 'cliente@brisa.com.br';

-- Insert new employee (technician)
INSERT INTO tb_employee (id, internal_code, first_name, last_name, cpf)
SELECT id, 'a1b2c3d4-e5f6-7890-1234-567890abcdef', 'Novo', 'Técnico', '98765432109'
FROM tb_user WHERE username = 'tecnico@brisa.com.br';

-- Assign roles to new users
-- Assign CLIENT role to new client user
INSERT INTO tb_user_roles (user_id, roles_id)
SELECT u.id, r.id
FROM tb_user u, tb_user_role r
WHERE u.username = 'cliente@brisa.com.br' AND r.name = 'CLIENT';

-- Assign TECHNICIAN role to new employee user
INSERT INTO tb_user_roles (user_id, roles_id)
SELECT u.id, r.id
FROM tb_user u, tb_user_role r
WHERE u.username = 'tecnico@brisa.com.br' AND r.name = 'TECHNICIAN';

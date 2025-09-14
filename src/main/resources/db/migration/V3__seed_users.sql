INSERT INTO tb_user (id, username, password, is_password_changed, is_active, created_at, updated_at)
VALUES (1,
        'admin@brisa.com.br',
        '{bcrypt}$2a$12$KHVRSpmjUnV4hhFq88HMme6CgYY5JZZkim/hBqRKAho.PgHGZ0cU.',
        true,
        true,
        now(),
        now()),

       (2,
        'user@brisa.com.br',
        '{bcrypt}$2a$12$fhzfkxdj6YQXrSL2HHmSpeBe9s3r2lYWWXbj0qNoLkCXjxqJ/5DEm',
        true,
        true,
        now(),
        now());

INSERT INTO tb_employee (id, internal_code, first_name, last_name, cpf)
VALUES (1,
        '0bd70579-2369-4fb2-a675-4c3953f17bfa',
        'Carlos',
        'Almeida',
        '23275817027');

INSERT INTO tb_client (id, name, cpf, address, phone, status)
VALUES (2,
        'Odilon',
        '71815826070',
        'Rua da BRISA',
        '11 99999-9999',
        true);

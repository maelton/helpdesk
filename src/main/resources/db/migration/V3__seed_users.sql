INSERT INTO tb_user (id, username, password, is_password_changed, is_active, created_at, updated_at)
VALUES (1,
        'admin@brisa.com.br',
        '{bcrypt}$2a$12$KHVRSpmjUnV4hhFq88HMme6CgYY5JZZkim/hBqRKAho.PgHGZ0cU.', -- admin
        true,
        true,
        now(),
        now()),

       (2,
        'user@brisa.com.br',
        '{bcrypt}$2a$12$fhzfkxdj6YQXrSL2HHmSpeBe9s3r2lYWWXbj0qNoLkCXjxqJ/5DEm', -- user
        true,
        true,
        now(),
        now()),

        (3,
            'carlos@brisa.com.br',
            '{bcrypt}$2a$12$Ws1Wj/GI2Xw5iQrddRWU/.1FOtS8n/GBysSVxJ5UAiDQwlzR5Wud6', -- carlos
            true,
            true,
            now(),
            now()),

        (4,
            'odilon@brisa.com.br',
            '{bcrypt}$2a$12$Sp979uNTcNxWX9wXgc6w3eJxuz8jaiNbCDXsrZXj2JrPm67G3B1ZC', -- odilon
            true,
            true,
            now(),
            now());

INSERT INTO tb_employee (id, internal_code, first_name, last_name, cpf)
VALUES (1,
        '0bd70579-2369-4fb2-a675-4c3953f17bfa',
        'Admin',
        'das Galáxias',
        '23275817027');

INSERT INTO tb_client (id, name, cpf, address, phone, status)
VALUES (2,
        'User',
        '71815826070',
        'Rua da BRISA',
        '11 99999-9999',
        true);

INSERT INTO tb_employee (id, internal_code, first_name, last_name, cpf)
VALUES (3,
        'a90c110c-d803-41c4-a518-e2bb100ae7bd',
        'Carlos',
        'das Galáxias',
        '34524605096');

INSERT INTO tb_client (id, name, cpf, address, phone, status)
VALUES (4,
        'Odilon',
        '92855684080',
        'Rua da BRISA',
        '11 99999-9999',
        true);
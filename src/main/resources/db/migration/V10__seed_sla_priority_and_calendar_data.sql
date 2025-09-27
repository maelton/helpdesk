INSERT INTO tb_sla_priority (name, description, level, color, is_active, created_at, updated_at)
VALUES
    ('Critical', 'Critical priority - immediate attention required', 1, '#FF0000', TRUE, NOW(), NOW()),
    ('High', 'High priority - urgent attention needed', 2, '#FF8C00', TRUE, NOW(), NOW()),
    ('Medium', 'Medium priority - normal processing', 3, '#FFD700', TRUE, NOW(), NOW()),
    ('Low', 'Low priority - can be delayed', 4, '#90EE90', TRUE, NOW(), NOW());

INSERT INTO tb_sla_calendar (name, description, timezone, consider_weekends, consider_holidays, is_active, created_at, updated_at)
VALUES
    ('24x7 Support', 'Round-the-clock support calendar', 'America/Sao_Paulo', TRUE, FALSE, TRUE, NOW(), NOW()),
    ('Business Hours', 'Standard business hours calendar', 'America/Sao_Paulo', FALSE, TRUE, TRUE, NOW(), NOW());

INSERT INTO tb_sla_day (calendar_id, day_of_week, start_time, end_time, is_working_day, created_at, updated_at)
VALUES
    (1, 'MONDAY', '00:00:00', '23:59:59', TRUE, NOW(), NOW()),
    (1, 'TUESDAY', '00:00:00', '23:59:59', TRUE, NOW(), NOW()),
    (1, 'WEDNESDAY', '00:00:00', '23:59:59', TRUE, NOW(), NOW()),
    (1, 'THURSDAY', '00:00:00', '23:59:59', TRUE, NOW(), NOW()),
    (1, 'FRIDAY', '00:00:00', '23:59:59', TRUE, NOW(), NOW()),
    (1, 'SATURDAY', '00:00:00', '23:59:59', TRUE, NOW(), NOW()),
    (1, 'SUNDAY', '00:00:00', '23:59:59', TRUE, NOW(), NOW());

INSERT INTO tb_sla_day (calendar_id, day_of_week, start_time, end_time, is_working_day, created_at, updated_at)
VALUES
    (2, 'MONDAY', '08:00:00', '18:00:00', TRUE, NOW(), NOW()),
    (2, 'TUESDAY', '08:00:00', '18:00:00', TRUE, NOW(), NOW()),
    (2, 'WEDNESDAY', '08:00:00', '18:00:00', TRUE, NOW(), NOW()),
    (2, 'THURSDAY', '08:00:00', '18:00:00', TRUE, NOW(), NOW()),
    (2, 'FRIDAY', '08:00:00', '18:00:00', TRUE, NOW(), NOW()),
    (2, 'SATURDAY', '08:00:00', '12:00:00', FALSE, NOW(), NOW()),
    (2, 'SUNDAY', '08:00:00', '18:00:00', FALSE, NOW(), NOW());

INSERT INTO tb_sla_exception (id, name, description, exception_date, type, is_recurring, is_active, created_at, updated_at)
VALUES
    (1, 'Natal 2026', 'Christmas Day', '2026-12-25', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (2, 'Ano Novo 2026', 'New Year Day', '2026-01-01', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (3, 'Independência do Brasil 2026', 'Brazil Independence Day', '2026-09-07', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (4, 'Tiradentes 2026', 'Tiradentes Day', '2026-04-21', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (5, 'Dia do Trabalhador 2026', 'Labor Day', '2026-05-01', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (6, 'Nossa Senhora Aparecida 2026', 'Our Lady of Aparecida', '2026-10-12', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (7, 'Finados 2026', 'All Souls’ Day', '2026-11-02', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (8, 'Proclamação da República 2026', 'Republic Proclamation Day', '2026-11-15', 'HOLIDAY', TRUE, TRUE, NOW(), NOW()),
    (9, 'Consciência Negra 2026', 'Black Awareness Day', '2026-11-20', 'HOLIDAY', TRUE, TRUE, NOW(), NOW());

INSERT INTO tb_sla_calendar_exception (calendar_id, exception_id)
VALUES
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 5),
    (2, 6),
    (2, 7),
    (2, 8),
    (2, 9);

UPDATE tb_sla SET 
    priority_id = 1,
    calendar_id = 1
WHERE id = 1;

UPDATE tb_sla SET 
    priority_id = 2,
    calendar_id = 2
WHERE id = 2;

UPDATE tb_sla SET 
    priority_id = 3,
    calendar_id = 2
WHERE id = 3;

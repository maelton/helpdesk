INSERT INTO tb_sla (name, description, response_time, resolution_time, is_active, created_at, updated_at)
VALUES
    ('Urgent', 'Urgent SLA - 15 minutes for response and resolution', 900, 900, true, NOW(), NOW()),
    ('Important', 'Important SLA - 30 minutes for response and resolution', 1800, 1800, true, NOW(), NOW()),
    ('Default', 'Default SLA - 1 hour for response and resolution', 3600, 3600, true, NOW(), NOW());
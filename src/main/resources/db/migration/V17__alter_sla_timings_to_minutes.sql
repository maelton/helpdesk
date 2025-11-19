-- Atualiza SLA 'Urgent'
-- Response: 15 min | Resolution: 60 min (1 hora)
UPDATE tb_sla
SET 
    response_time = 15,
    resolution_time = 60,
    description = 'Urgent SLA - 15 min response, 1h resolution',
    updated_at = NOW()
WHERE name = 'Urgent';

-- Atualiza SLA 'Important'
-- Response: 30 min | Resolution: 120 min (2 horas)
UPDATE tb_sla
SET 
    response_time = 30,
    resolution_time = 120,
    description = 'Important SLA - 30 min response, 2h resolution',
    updated_at = NOW()
WHERE name = 'Important';

-- Atualiza SLA 'Default'
-- Response: 60 min (1 hora) | Resolution: 480 min (8 horas/1 dia útil)
UPDATE tb_sla
SET 
    response_time = 60,
    resolution_time = 480,
    description = 'Default SLA - 1 hour response, 8h resolution',
    updated_at = NOW()
WHERE name = 'Default';
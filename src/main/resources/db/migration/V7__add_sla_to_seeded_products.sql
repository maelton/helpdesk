UPDATE tb_product
SET sla_id = CASE id
                 WHEN 1 THEN 1
                 WHEN 2 THEN 2
                 WHEN 3 THEN 3
             END
WHERE id IN (1,2,3);
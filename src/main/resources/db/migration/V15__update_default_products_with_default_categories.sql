UPDATE tb_product
SET category_id = (SELECT id FROM tb_product_category WHERE name = 'Software')
WHERE internal_code = '89e2062a-9fbd-4d3a-bcd6-9bfe8580fb5c';

UPDATE tb_product
SET category_id = (SELECT id FROM tb_product_category WHERE name = 'Hardware')
WHERE internal_code = '98f3920b-a30c-403b-b7a0-4380565a2fad';

UPDATE tb_product
SET category_id = (SELECT id FROM tb_product_category WHERE name = 'Services')
WHERE internal_code = 'd354dd7f-5cb9-4888-bc73-a4d1454735b8';

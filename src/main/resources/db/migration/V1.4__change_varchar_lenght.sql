ALTER TABLE users.countries ALTER COLUMN name TYPE VARCHAR(50);
ALTER TABLE users.users ALTER COLUMN telephone_num TYPE VARCHAR(15);

-- Cambiar el tipo de datos y agregar restricción NOT NULL

ALTER TABLE users.refresh_tokens ALTER COLUMN device_id TYPE UUID USING device_id::uuid;
ALTER TABLE users.refresh_tokens ALTER COLUMN device_id SET NOT NULL;

ALTER TABLE users.refresh_tokens ALTER COLUMN token_family_id TYPE UUID USING token_family_id::uuid;
ALTER TABLE users.refresh_tokens ALTER COLUMN token_family_id SET NOT NULL;
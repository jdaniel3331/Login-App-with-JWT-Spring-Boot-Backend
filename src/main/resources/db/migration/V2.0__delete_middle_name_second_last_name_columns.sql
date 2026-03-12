ALTER TABLE users.users DROP COLUMN middle_name, DROP COLUMN second_last_name;

ALTER TABLE users.users RENAME COLUMN first_name TO name;
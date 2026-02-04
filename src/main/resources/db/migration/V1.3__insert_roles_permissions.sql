INSERT INTO users.roles (name) VALUES
('ADMIN'),
('USER');

INSERT INTO users.permissions (name) VALUES
('user:read'), -- Listar usuarios y ver sus perfiles
('user:write'), -- Editar información
('user:delete'), -- Eliminar cuentas
('auth:role_assign'), -- Asignar o cambiar roles
('auth:password_update'), -- Actualizar contraseña
('auth:lock'), -- Suspender una cuenta
('auth:unlock'), -- Quitar la suspención de una cuenta
('session:read'), -- Listar sesiones activas
('session:revoke'), -- Cerrar una sesión específica
('role:read'); -- Ver la lista de roles y los permisos de cada uno

-- Agregar permisos al rol ADMIN
INSERT INTO users.roles_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM users.roles r, users.permissions p
WHERE r.name = 'ADMIN';

-- Agregar permisos al rol USER
INSERT INTO users.roles_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM users.roles r, users.permissions p
WHERE r.name = 'USER' AND p.name NOT IN ('auth:role_assign', 'auth:lock', 'auth:unlock', 'role:read');
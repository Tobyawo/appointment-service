-- Create table
CREATE TABLE role_permissions (
                                  role_id BIGINT NOT NULL,
                                  permission_id BIGINT NOT NULL,
                                  PRIMARY KEY (role_id, permission_id),
                                  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role(id),
                                  CONSTRAINT fk_permission FOREIGN KEY (permission_id) REFERENCES permission(id)
);

-- Insert into permission
INSERT INTO permission (name) VALUES ('admin:read');
INSERT INTO permission (name) VALUES ('admin:write');
INSERT INTO permission (name) VALUES ('member:read');
INSERT INTO permission (name) VALUES ('member:write');

-- Insert into role
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('MEMBER');

-- Insert into role_permissions for ADMIN
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM role r, permission p
WHERE r.name = 'ADMIN' AND p.name LIKE 'admin:%';

-- Insert into role_permissions for MEMBER
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM role r, permission p
WHERE r.name = 'MEMBER' AND p.name LIKE 'member:%';

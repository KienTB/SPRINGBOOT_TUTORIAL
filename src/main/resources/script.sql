CREATE TABLE data.users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    PRIMARY KEY (username)
);
CREATE TABLE data.authorities (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    authority VARCHAR(50),
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES data.users (username) ON DELETE CASCADE
);

DELETE FROM users WHERE username = 'kienuser';

DELETE FROM authorities
WHERE username NOT IN ('kienadmin', 'kienuser');

CREATE TABLE data.customer (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(500),
    role varchar(50)
);

INSERT INTO customer (id, username, password, role)
VALUES (1, 'kiendepzai2k3', 'password', 'admin');

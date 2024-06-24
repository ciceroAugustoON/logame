CREATE TABLE IF NOT EXISTS game (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    releaseyear INT,
    genre VARCHAR(256),
    scope VARCHAR(256),
    icon VARCHAR(64),
    cover VARCHAR(64)
);
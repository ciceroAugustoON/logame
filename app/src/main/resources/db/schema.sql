CREATE TABLE IF NOT EXISTS game (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(256) NOT NULL,
    release_year INT,
    genre VARCHAR(128),
    scope VARCHAR(128),
    icon VARCHAR(64),
    cover VARCHAR(64)
);
CREATE TABLE IF NOT EXISTS played_time (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    platform VARCHAR(128),
    finished_date DATE,
    minutes_played INT,
    state VARCHAR(16),
    game_id INT,
    FOREIGN KEY(game_id) REFERENCES game(id)
);
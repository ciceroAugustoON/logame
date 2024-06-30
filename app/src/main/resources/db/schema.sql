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
    state VARCHAR(16) NOT NULL,
    platform VARCHAR(128),
    finished_date DATE,
    minutes_played INT,
    game_id INT,
    FOREIGN KEY(game_id) REFERENCES game(id)
);
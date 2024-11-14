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

INSERT INTO game (name, release_year, genre, scope) VALUES 
('The Legend of Zelda: Breath of the Wild', 2017, 'Action-Adventure', 'Open World'),
('Minecraft', 2011, 'Sandbox', 'Open World'),
('The Witcher 3: Wild Hunt', 2015, 'RPG', 'Open World'),
('Among Us', 2018, 'Party', 'Multiplayer'),
('Celeste', 2018, 'Platformer', 'Single Player');


INSERT INTO played_time (state, platform, finished_date, minutes_played, game_id) VALUES
('FINISHED', 'Nintendo Switch', '2020-10-15', 3000, 1),
('PLAYING', 'PC', '2024-05-20', 4500, 2),
('FINISHED', 'PlayStation 4', '2017-07-10', 5200, 3),
('BACKLOG', 'Mobile', '2023-03-11', 0, 4),
('FINISHED', 'PC', '2019-02-20', 800, 5);

package logame.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import logame.entities.Game;

public class GameRepositoryTest {

    @Test
    void crud1() {
        GameRepository gameRepository = new GameRepository();
        Game g = new Game("Hollow Knight");
        gameRepository.create(g);

        assertEquals(g.getName(), gameRepository.findById(g.getId()).getName());
    }
    
}

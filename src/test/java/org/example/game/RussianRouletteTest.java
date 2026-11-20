package org.example.game;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RussianRouletteTest {

    @Test
    void winPaysHalfBet() {
        RussianRoulette game = new RussianRoulette(new Random(1), 2, 3);
        assertEquals(50, game.play(100));
    }

    @Test
    void losePaysFullBet() {
        RussianRoulette game = new RussianRoulette(new Random(1), 4, 1);
        assertEquals(-100, game.play(100));
    }

    @Test
    void lastChamberSafeWhenFiveBullets() {
        RussianRoulette game = new RussianRoulette(new Random(1), 5, 5);
        assertEquals(50, game.play(100));
    }

    @Test
    void firstChamberLethalWhenOneBullet() {
        RussianRoulette game = new RussianRoulette(new Random(1), 1, 0);
        assertEquals(-100, game.play(100));
    }

    @Test
    void oddBetIsRoundedDown() {
        RussianRoulette game = new RussianRoulette(new Random(1), 1, 5);
        assertEquals(50, game.play(101));
    }

    @Test
    void bulletsStayInRange() {
        Random random = new Random(42);
        for (int i = 0; i < 1000; i++) {
            RussianRoulette game = new RussianRoulette(random);
            assertNotNull(game);
        }
    }

    @Test
    void textShowsOutcome() {
        RussianRoulette win = new RussianRoulette(new Random(1), 1, 2);
        win.play(100);
        assertTrue(win.result().contains("вхолостую"));
        assertTrue(win.result().contains("+50"));

        RussianRoulette lose = new RussianRoulette(new Random(1), 3, 1);
        lose.play(100);
        assertTrue(lose.result().contains("Выстрел"));
        assertTrue(lose.result().contains("-100"));
    }
}
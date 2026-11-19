package org.example.LV2;

import org.example.Phrases;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Level2Test {

    private final String error = new Phrases().GetError();

    @Test
    void keepsStartingDeposit() { assertEquals(2000, new Level2(2000).GetDeposit()); }

    @Test
    void colorBetWithinBounds() {
        Level2 level = new Level2(2000);
        level.Game_level2("roulette", 100, "красное");
        assertTrue(level.GetDeposit() >= 1900 && level.GetDeposit() <= 2100);
    }

    @Test
    void zeroBetWithinBounds() {
        Level2 level = new Level2(2000);
        level.Game_level2("roulette", 100, "зеро");
        assertTrue(level.GetDeposit() >= 1900 && level.GetDeposit() <= 5500);
    }

    @Test
    void unknownBetTypeRejected() {
        Level2 level = new Level2(2000);
        assertEquals(error, level.Game_level2("roulette", 100, "фиолетовое"));
        assertEquals(2000, level.GetDeposit());
    }

    @Test
    void unknownGameRejected() {
        Level2 level = new Level2(2000);
        assertEquals(error, level.Game_level2("poker", 100, "красное"));
    }

    @Test
    void russianRouletteIsStub() {
        assertEquals("пока не готова", new Level2(2000).Game_level2("russian_roulette", 100, "красное"));
    }

    @Test
    void invalidBetRejected() {
        Level2 level = new Level2(2000);
        assertEquals(error, level.Game_level2("roulette", 2001, "красное"));
        assertEquals(2000, level.GetDeposit());
    }
}
package org.example.hall;

import org.example.game.Roulette;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Level2Test {

    private final Level hall = new Level2(new Random(1));

    @Test
    void numberIsTwo() { assertEquals(2, hall.number()); }

    @Test
    void hasRouletteAndRussianRoulette() {
        assertTrue(hall.has("roulette"));
        assertTrue(hall.has("russian_roulette"));
        assertFalse(hall.has("bones"));
    }

    @Test
    void createsRoulette() { assertInstanceOf(Roulette.class, hall.create("roulette", "красное")); }

    @Test
    void unknownColorIsNull() { assertNull(hall.create("roulette", "фиолетовое")); }

    @Test
    void thresholdIsTwoThousand() { assertEquals(2000, hall.threshold()); }

    @Test
    void rouletteHasThreeOptions() {
        assertEquals(List.of("красное", "черное", "зеро"), hall.options("roulette"));
    }

    @Test
    void otherGamesHaveNoOptions() { assertTrue(hall.options("russian_roulette").isEmpty()); }

    @Test
    void missingOptionIsNull() { assertNull(hall.create("roulette")); }

    @Test
    void russianRouletteNotReadyYet() { assertNull(hall.create("russian_roulette", "красное")); }

    @Test
    void unknownGameIsNull() { assertNull(hall.create("poker", "красное")); }
}
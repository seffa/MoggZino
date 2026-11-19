package org.example.game;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RouletteTest {

    @Test
    void redWinsOnRed() { assertEquals(10, Roulette.payout(1, Color.RED, 10)); }

    @Test
    void blackWinsOnBlack() { assertEquals(10, Roulette.payout(2, Color.BLACK, 10)); }

    @Test
    void zeroBeatsBothColors() {
        assertEquals(-10, Roulette.payout(0, Color.RED, 10));
        assertEquals(-10, Roulette.payout(0, Color.BLACK, 10));
    }

    @Test
    void zeroPaysThirtyFive() { assertEquals(350, Roulette.payout(0, Color.ZERO, 10)); }

    @Test
    void wrongColorLoses() { assertEquals(-10, Roulette.payout(2, Color.RED, 10)); }

    @Test
    void wheelHasEighteenRedEighteenBlackOneZero() {
        int red = 0, black = 0, zero = 0;
        for (int number = 0; number <= 36; number++) {
            switch (Color.of(number)) {
                case RED -> red++;
                case BLACK -> black++;
                case ZERO -> zero++;
            }
        }
        assertEquals(18, red);
        assertEquals(18, black);
        assertEquals(1, zero);
    }

    @Test
    void everyBetKeepsHouseEdge() {
        for (Color color : Color.values()) {
            int total = 0;
            for (int number = 0; number <= 36; number++) total += Roulette.payout(number, color, 37);
            assertEquals(-37, total, "сломан баланс на ставке " + color);
        }
    }

    @Test
    void numberStaysInRange() {
        Random random = new Random(3);
        for (int i = 0; i < 1000; i++) {
            int number = new Roulette(random, Color.RED).number();
            assertTrue(number >= 0 && number <= 36);
        }
    }

    @Test
    void textMappingWorks() {
        assertEquals(Color.RED, Color.fromText("красное"));
        assertEquals(Color.ZERO, Color.fromText("Зеро"));
        assertNull(Color.fromText("фиолетовое"));
    }
}
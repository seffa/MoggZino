package org.example.game;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UpgraderTest {

    @Test
    void multiplierCalculationIsCorrect() {
        assertEquals(11.0, Upgrader.calculateMultiplier(1000, 2000, 100));
    }

    @Test
    void chanceCalculationIsCorrect() {
        assertEquals(0.08636, Upgrader.calculateChance(11.0), 0.0001);
    }

    @Test
    void winReturnsTargetDifference() {
        // Баланс 1000, цель 2000. Форсируем выигрышный ролл (0).
        Upgrader game = new Upgrader(new Random(1), 1000, 2000, 0);
        int payout = game.play(100);

        // Выигрыш должен вернуть дельту: 2000 - 1000 = 1000
        assertEquals(1000, payout);
    }

    @Test
    void loseReturnsNegativeBet() {
        Upgrader game = new Upgrader(new Random(1), 1000, 2000, 9999);
        int payout = game.play(100);

        assertEquals(-100, payout);
    }

    @Test
    void boundaryWinLossCheck() {
        assertTrue(Upgrader.isWin(863, 0.08636));
        assertFalse(Upgrader.isWin(864, 0.08636));
    }

    @Test
    void houseEdgeAlwaysHolds() {
        Random random = new Random(42);
        int currentBalance = 1000;
        int target = 2000;
        int bet = 100;
        int rounds = 100_000;

        int totalDelta = 0;
        for (int i = 0; i < rounds; i++) {
            Upgrader game = new Upgrader(random, currentBalance, target);
            totalDelta += game.play(bet);
        }

        double expectedLoss = rounds * bet * -0.05;
        assertTrue(Math.abs(totalDelta - expectedLoss) < (rounds * bet * 0.05),
                "RTP апгрейдера не сходится: " + totalDelta);
    }

    @Test
    void textShowsDetails() {
        Upgrader game = new Upgrader(new Random(1), 1000, 2000, 0);
        game.play(100);

        String result = game.result();
        assertTrue(result.contains("Цель: 2000"));
        assertTrue(result.contains("Ставка: 100"));
        assertTrue(result.contains("x11.00"));
        assertTrue(result.contains("8.6%"));
        assertTrue(result.contains("УСПЕХ"));
    }
}
package org.example.game;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BonesTest {

    @Test
    void sevenPaysTriple() { assertEquals(30, Bones.payout(new Roll(3, 4), 10)); }

    @Test
    void elevenPaysTriple() { assertEquals(30, Bones.payout(new Roll(5, 6), 10)); }

    @Test
    void twoLoses() { assertEquals(-10, Bones.payout(new Roll(1, 1), 10)); }

    @Test
    void twelveLoses() { assertEquals(-10, Bones.payout(new Roll(6, 6), 10)); }

    @Test
    void otherSumsReturnBet() {
        for (int first = 1; first <= 6; first++) {
            for (int second = 1; second <= 6; second++) {
                int sum = first + second;
                if (sum == 7 || sum == 11 || sum == 2 || sum == 12) continue;
                assertEquals(0, Bones.payout(new Roll(first, second), 10));
            }
        }
    }

    @Test
    void payoutScalesWithBet() { assertEquals(3, Bones.payout(new Roll(3, 4), 1)); }

    @Test
    void diceStayInRange() {
        Random random = new Random(1);
        for (int i = 0; i < 1000; i++) {
            Roll roll = new Bones(random).roll();
            assertTrue(roll.first() >= 1 && roll.first() <= 6);
            assertTrue(roll.second() >= 1 && roll.second() <= 6);
        }
    }

    @Test
    void textShowsSum() { assertEquals("🎲 3 + 4 = 7", Bones.text(new Roll(3, 4))); }
}
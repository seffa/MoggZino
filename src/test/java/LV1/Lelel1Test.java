package org.example.LV1;

import org.example.Phrases;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Level1Test {

    private final String error = new Phrases().GetError();

    @Test
    void startsWithHundred() { assertEquals(100, new Level1().GetDeposit()); }

    @Test
    void zeroBetRejected() {
        Level1 level = new Level1();
        assertEquals(error, level.Game_level1("bones", 0));
        assertEquals(100, level.GetDeposit());
    }

    @Test
    void negativeBetRejected() {
        Level1 level = new Level1();
        assertEquals(error, level.Game_level1("slots", -5));
        assertEquals(100, level.GetDeposit());
    }

    @Test
    void betAboveDepositRejected() {
        Level1 level = new Level1();
        assertEquals(error, level.Game_level1("bones", 101));
        assertEquals(100, level.GetDeposit());
    }

    @Test
    void unknownGameRejected() {
        Level1 level = new Level1();
        assertEquals(error, level.Game_level1("poker", 10));
        assertEquals(100, level.GetDeposit());
    }

    @Test
    void bonesChangesDepositWithinBounds() {
        Level1 level = new Level1();
        String answer = level.Game_level1("bones", 10);
        assertTrue(answer.contains("твой депозит"));
        assertTrue(level.GetDeposit() >= 90 && level.GetDeposit() <= 130);
    }

    @Test
    void slotsChangesDepositWithinBounds() {
        Level1 level = new Level1();
        level.Game_level1("slots", 10);
        assertTrue(level.GetDeposit() >= 90 && level.GetDeposit() <= 200);
    }

    @Test
    void depositNeverGoesNegative() {
        Level1 level = new Level1();
        for (int i = 0; i < 500 && level.GetDeposit() > 0; i++) {
            int bet = Math.min(10, level.GetDeposit());
            level.Game_level1(i % 2 == 0 ? "bones" : "slots", bet);
            assertTrue(level.GetDeposit() >= 0);
        }
    }
}
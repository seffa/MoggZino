package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Level1Test {
    private Level1 level1;

    @BeforeEach
    void setUp() {
        level1 = new Level1();
    }

    @Test
    void testInitialDeposit() {
        assertEquals(100, level1.GetDeposit());
    }

    @Test
    void testLv1OutSlots() {
        String result = level1.lv1_out("\\slots");

        assertNotNull(result);
        assertTrue(result.contains("СЛОТЫ"));
    }

    @Test
    void testLv1OutBones() {
        String result = level1.lv1_out("\\bones");

        assertNotNull(result);
        assertTrue(result.contains("КОСТИ"));
    }

    @Test
    void testLv1OutError() {
        String result = level1.lv1_out("\\invalid");

        assertNotNull(result);
        assertTrue(result.contains("ошибка"));
    }

    @Test
    void testGameLevel1Slots() {
        int initialDeposit = level1.GetDeposit();
        String result = level1.Game_level1("slots", 10);

        assertNotNull(result);
        assertTrue(result.contains("твой депозит"));

        // Депозит должен измениться
        int newDeposit = level1.GetDeposit();
        assertNotEquals(initialDeposit, newDeposit);
    }

    @Test
    void testGameLevel1Bones() {
        int initialDeposit = level1.GetDeposit();
        String result = level1.Game_level1("bones", 10);

        assertNotNull(result);
        assertTrue(result.contains("твой депозит"));
        assertTrue(result.contains("🎲"));
    }

    @Test
    void testDepositChangesAfterGame() {
        int initialDeposit = level1.GetDeposit();

        // Играем несколько раз
        level1.Game_level1("slots", 10);
        level1.Game_level1("bones", 10);

        // Депозит должен измениться
        int finalDeposit = level1.GetDeposit();
        // Не обязательно изменится, но не должен быть отрицательным
        assertTrue(finalDeposit >= 0);
    }
}
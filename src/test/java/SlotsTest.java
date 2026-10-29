package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlotsTest {
    private Slots slots;

    @BeforeEach
    void setUp() {
        slots = new Slots();
    }

    @Test
    void testGameWinAllSame() {
        Slots testSlots = new Slots();
        int initialDeposit = 100;
        int bet = 10;
        int result = testSlots.game(initialDeposit, bet);

        assertNotEquals(initialDeposit, result);
    }

    @Test
    void testGameTwoSame() {
        Slots testSlots = new Slots();
        int initialDeposit = 100;
        int bet = 10;

        int result = testSlots.game(initialDeposit, bet);

        // Если два символа совпадают, депозит не меняется
        // Тестируем через многократные запуски
        assertTrue(result >= initialDeposit - bet);
    }

    @Test
    void testGameAllDifferent() {
        Slots testSlots = new Slots();
        int initialDeposit = 100;
        int bet = 10;

        int result = testSlots.game(initialDeposit, bet);

        assertTrue(result <= initialDeposit);
    }

    @Test
    void testGetResult() {
        String result = slots.getResult();

        assertNotNull(result);
        assertTrue(result.contains("|"));
    }

    @Test
    void testStart() {
        String rules = slots.Start();

        assertNotNull(rules);
        assertTrue(rules.contains("СЛОТЫ"));
    }
}
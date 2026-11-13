package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlotsTest {

    @Test
    void testGame() {
        Slots s = new Slots();
        int d = 100;
        int b = 10;
        int r = s.game(d, b);
        assertNotEquals(d, r);
    }

    @Test
    void testGame2() {
        Slots s = new Slots();
        int d = 100;
        int b = 10;
        int r = s.game(d, b);
        assertTrue(r >= d - b);
    }

    @Test
    void testGame3() {
        Slots s = new Slots();
        int d = 100;
        int b = 10;
        int r = s.game(d, b);
        assertTrue(r <= d);
    }

    @Test
    void testGetResult() {
        Slots s = new Slots();
        String r = s.getResult();
        assertNotNull(r);
        assertTrue(r.contains("|"));
    }

    @Test
    void testStart() {
        Slots s = new Slots();
        String r = s.Start();
        assertNotNull(r);
        assertTrue(r.contains("СЛОТЫ"));
    }
}
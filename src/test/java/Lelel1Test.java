package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Level1Test {

    @Test
    void testInitialDeposit() {
        Level1 l = new Level1();
        assertEquals(100, l.GetDeposit());
    }

    @Test
    void testLv1OutSlots() {
        Level1 l = new Level1();
        String r = l.lv1_out("\\slots");
        assertNotNull(r);
        assertTrue(r.contains("СЛОТЫ"));
    }

    @Test
    void testLv1OutBones() {
        Level1 l = new Level1();
        String r = l.lv1_out("\\bones");
        assertNotNull(r);
        assertTrue(r.contains("КОСТИ") || r.contains("кости"));
    }

    @Test
    void testLv1OutError() {
        Level1 l = new Level1();
        String r = l.lv1_out("\\invalid");
        assertNotNull(r);
        assertTrue(r.contains("ошибка"));
    }

    @Test
    void testGameLevel1Slots() {
        Level1 l = new Level1();
        int d = l.GetDeposit();
        String r = l.Game_level1("slots", 10);
        assertNotNull(r);
        assertTrue(r.contains("твой депозит"));
        int d2 = l.GetDeposit();
        assertTrue(d2 >= d - 10 && d2 <= d + 100);
    }

    @Test
    void testGameLevel1Bones() {
        Level1 l = new Level1();
        int d = l.GetDeposit();
        String r = l.Game_level1("bones", 10);
        assertNotNull(r);
        assertTrue(r.contains("твой депозит"));
        int d2 = l.GetDeposit();
        assertTrue(d2 >= d - 10 && d2 <= d + 30);
    }
}
package org.example;

import org.example.LV1.Bones;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BonesTest {

    @Test
    void testGame() {
        Bones bones = new Bones();
        int d = 100;
        int b = 10;
        int r = bones.game(d, b);
        assertTrue(r >= d - b && r <= d + b * 3);
    }

    @Test
    void testGame2() {
        Bones bones = new Bones();
        int d = 100;
        int b = 10;
        int r = bones.game(d, b);
        assertTrue(r <= d + b * 3);
    }

    @Test
    void testGame3() {
        Bones bones = new Bones();
        int d = 100;
        int b = 10;
        int r = bones.game(d, b);
        assertTrue(r >= d - b);
    }

    @Test
    void testGetResult() {
        Bones bones = new Bones();
        String r = bones.getResult();
        assertNotNull(r);
        assertTrue(r.contains("🎲"));
        assertTrue(r.contains("+"));
        assertTrue(r.contains("="));
    }

    @Test
    void testDiceRange() {
        for (int i = 0; i < 100; i++) {
            Bones bones = new Bones();
            String r = bones.getResult();
            String[] p = r.split("[^0-9]+");
            int d1 = Integer.parseInt(p[1]);
            int d2 = Integer.parseInt(p[2]);
            assertTrue(d1 >= 1 && d1 <= 6);
            assertTrue(d2 >= 1 && d2 <= 6);
        }
    }
}
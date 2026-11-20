package org.example.hall;

import org.example.game.Bones;
import org.example.game.Slots;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Level1Test {

    private final Level hall = new Level1(new Random(1));

    @Test
    void numberIsOne() { assertEquals(1, hall.number()); }

    @Test
    void hasBonesAndSlots() {
        assertTrue(hall.has("bones"));
        assertTrue(hall.has("slots"));
        assertFalse(hall.has("roulette"));
    }

    @Test
    void thresholdIsZero() { assertEquals(0, hall.threshold()); }

    @Test
    void createsBones() { assertInstanceOf(Bones.class, hall.create("bones")); }

    @Test
    void createsSlots() { assertInstanceOf(Slots.class, hall.create("slots")); }

    @Test
    void unknownGameIsNull() { assertNull(hall.create("poker")); }

    @Test
    void createsFreshInstances() { assertNotSame(hall.create("bones"), hall.create("bones")); }
}
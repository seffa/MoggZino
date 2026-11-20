package org.example.hall;

import org.example.game.Slots;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Level3Test {

    private final Level hall = new Level3(new Random(1));

    @Test
    void numberIsThree() { assertEquals(3, hall.number()); }

    @Test
    void thresholdIsFiveThousand() { assertEquals(5000, hall.threshold()); }

    @Test
    void hasSlots() {
        assertTrue(hall.has("slots"));
        assertFalse(hall.has("bones"));
    }

    @Test
    void createsSlots() { assertInstanceOf(Slots.class, hall.create("slots")); }

    @Test
    void unknownGameIsNull() { assertNull(hall.create("blackjack")); }
}
package org.example.hall;

import org.example.game.Blackjack;
import org.example.game.Upgrader;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Level3Test {

    private final Level hall = new Level3(new Random(1));

    @Test
    void numberIsThree() { assertEquals(3, hall.number()); }

    @Test
    void thresholdIsFiveThousand() { assertEquals(5000, hall.threshold()); }

    @Test
    void hasUpgraderAndBlackjack() {
        assertTrue(hall.has("upgrader"));
        assertTrue(hall.has("blackjack")); // <--- Добавили проверку
        assertFalse(hall.has("bones"));
    }

    @Test
    void createsUpgrader() {
        assertInstanceOf(Upgrader.class, hall.create("upgrader", "Цель x2", 1000));
    }

    @Test
    void createsBlackjack() {
        assertInstanceOf(Blackjack.class, hall.create("blackjack", null, 1000));
    }

    @Test
    void unknownGameIsNull() {
        assertNull(hall.create("poker", null, 1000));
    }
}
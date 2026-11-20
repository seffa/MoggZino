package org.example.hall;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LevelsTest {

    @Test
    void createsFirstHall() { assertInstanceOf(Level1.class, Levels.create(1, new Random(1))); }

    @Test
    void createsThirdHall() { assertInstanceOf(Level3.class, Levels.create(3, new Random(1))); }

    @Test
    void createsSecondHall() { assertInstanceOf(Level2.class, Levels.create(2, new Random(1))); }

    @Test
    void unknownNumberIsNull() { assertNull(Levels.create(9, new Random(1))); }
}
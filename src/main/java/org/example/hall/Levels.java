package org.example.hall;

import java.util.Random;

public class Levels {
    public static Level create(int number, Random random) {
        return switch (number) {
            case 1 -> new Level1(random);
            case 2 -> new Level2(random);
            case 3 -> new Level3(random);
            default -> null;
        };
    }
}
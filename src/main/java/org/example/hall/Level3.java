package org.example.hall;

import org.example.game.QuickGame;
import org.example.game.Slots;

import java.util.List;
import java.util.Random;

public class Level3 extends Level {
    public static final int THRESHOLD = 5000;

    private final Random random;

    public Level3(Random random) { this.random = random; }

    @Override public int number() { return 3; }
    @Override public int threshold() { return THRESHOLD; }
    @Override public List<String> ids() { return List.of("slots"); }

    @Override
    public QuickGame create(String id, String option) {
        if (!id.equals("slots")) return null;
        return new Slots(random);
    }
}
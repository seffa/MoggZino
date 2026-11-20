package org.example.hall;

import org.example.game.Bones;
import org.example.game.QuickGame;
import org.example.game.Slots;

import java.util.List;
import java.util.Random;

public class Level1 extends Level {
    private final Random random;

    public Level1(Random random) { this.random = random; }

    @Override public int number() { return 1; }
    @Override public int threshold() { return 0; }
    @Override public List<String> ids() { return List.of("bones", "slots"); }

    @Override
    public QuickGame create(String id, String option, int balance) { // ДОБАВИЛИ balance
        return switch (id) {
            case "bones" -> new Bones(random);
            case "slots" -> new Slots(random);
            default -> null;
        };
    }
}
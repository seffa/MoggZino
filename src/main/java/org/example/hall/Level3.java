package org.example.hall;

import org.example.game.Blackjack;
import org.example.game.Game;
import org.example.game.Upgrader;

import java.util.List;
import java.util.Random;

public class Level3 extends Level {
    public static final int THRESHOLD = 5000;
    private final Random random;

    public Level3(Random random) { this.random = random; }

    @Override public int number() { return 3; }
    @Override public int threshold() { return THRESHOLD; }
    @Override public List<String> ids() { return List.of("upgrader", "blackjack"); }

    @Override
    public List<String> options(String id) {
        if (id.equals("upgrader")) return List.of("Цель x2", "Цель x5", "Цель x10");
        return List.of();
    }

    @Override
    public Game create(String id, String option, int balance) {
        if (id.equals("upgrader")) {
            int target = switch (option) {
                case "Цель x2" -> balance * 2;
                case "Цель x5" -> balance * 5;
                case "Цель x10" -> balance * 10;
                default -> balance * 2;
            };
            return new Upgrader(random, balance, target);
        }
        if (id.equals("blackjack")) {
            return new Blackjack(random);
        }
        return null;
    }
}
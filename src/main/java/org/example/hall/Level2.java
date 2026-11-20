package org.example.hall;

import org.example.game.Color;
import org.example.game.QuickGame;
import org.example.game.Roulette;
import org.example.game.RussianRoulette; // <--- Добавили импорт

import java.util.List;
import java.util.Random;

public class Level2 extends Level {
    public static final int THRESHOLD = 2000;

    private final Random random;

    public Level2(Random random) { this.random = random; }

    @Override public int number() { return 2; }
    @Override public int threshold() { return THRESHOLD; }
    @Override public List<String> ids() { return List.of("roulette", "russian_roulette"); }

    @Override
    public List<String> options(String id) {
        return id.equals("roulette") ? List.of("красное", "черное", "зеро") : List.of();
    }

    @Override
    public QuickGame create(String id, String option, int balance) {
        if (id.equals("roulette")) {
            Color color = Color.fromText(option);
            if (color == null) return null;
            return new Roulette(random, color);
        }
        if (id.equals("russian_roulette")) {
            return new RussianRoulette(random);
        }
        return null;
    }
}
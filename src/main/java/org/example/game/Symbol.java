package org.example.game;

import java.util.Random;

public enum Symbol {
    CHERRY("🍒", 40, 2),
    LEMON("🍋", 30, 3),
    BAR("BAR", 20, 5),
    SEVEN("7", 10, 10);

    private final String label;
    private final int weight;
    private final int multiplier;

    Symbol(String label, int weight, int multiplier) {
        this.label = label;
        this.weight = weight;
        this.multiplier = multiplier;
    }

    public String label() { return label; }
    public int weight() { return weight; }
    public int multiplier() { return multiplier; }

    public static Symbol pick(Random random) {
        int roll = random.nextInt(100);
        int border = 0;
        for (Symbol symbol : values()) {
            border += symbol.weight;
            if (roll < border) return symbol;
        }
        return SEVEN;
    }
}
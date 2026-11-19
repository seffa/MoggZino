package org.example.game;

import org.example.Phrases;

import java.util.Random;

public class Bones extends QuickGame {
    private final Roll roll;

    public Bones(Random random) {
        super(random);
        roll = new Roll(random.nextInt(6) + 1, random.nextInt(6) + 1);
    }

    @Override public String id() { return "bones"; }
    @Override public String rules() { return new Phrases().GetRulesBones(); }

    public static int payout(Roll roll, int bet) {
        int sum = roll.sum();
        if (sum == 7 || sum == 11) return bet * 3;
        if (sum == 2 || sum == 12) return -bet;
        return 0;
    }

    public static String text(Roll roll) {
        return "🎲 " + roll.first() + " + " + roll.second() + " = " + roll.sum();
    }

    @Override public int play(int bet) { return payout(roll, bet); }
    @Override public String result() { return text(roll); }
    public Roll roll() { return roll; }
}
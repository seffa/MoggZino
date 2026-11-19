package org.example.game;

import org.example.Phrases;

import java.util.Random;

public class Roulette extends QuickGame {
    private final int number;
    private final Color betOn;

    public Roulette(Random random, Color betOn) {
        super(random);
        this.betOn = betOn;
        number = random.nextInt(37);
    }

    @Override public String id() { return "roulette"; }
    @Override public String rules() { return new Phrases().GetRoulette(); }

    public static int payout(int number, Color betOn, int bet) {
        if (Color.of(number) != betOn) return -bet;
        return betOn == Color.ZERO ? bet * 35 : bet;
    }

    @Override public int play(int bet) { return payout(number, betOn, bet); }

    @Override public String result() {
        Color color = Color.of(number);
        return color.mark() + " выпало " + number + " (" + color.text() + ")";
    }

    public int number() { return number; }
}
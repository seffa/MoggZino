package org.example.game;

import org.example.Phrases;

import java.util.Random;

public class Slots extends QuickGame {
    private static final int REELS = 3;
    private final Symbol[] spin = new Symbol[REELS];

    public Slots(Random random) {
        super(random);
        for (int i = 0; i < REELS; i++) spin[i] = Symbol.pick(random);
    }

    @Override public String id() { return "slots"; }
    @Override public String rules() { return new Phrases().GetSlots(); }

    public static int payout(Symbol[] spin, int bet) {
        int best = 1;
        for (Symbol symbol : spin) {
            int count = 0;
            for (Symbol other : spin) {
                if (other == symbol) count++;
            }
            best = Math.max(best, count);
        }
        if (best == spin.length) return bet * spin[0].multiplier();
        if (best >= 2) return 0;
        return -bet;
    }

    public static String text(Symbol[] spin) {
        StringBuilder line = new StringBuilder();
        for (Symbol symbol : spin) {
            if (line.length() > 0) line.append(" | ");
            line.append(symbol.label());
        }
        return line.toString();
    }

    @Override public int play(int bet) { return payout(spin, bet); }
    @Override public String result() { return text(spin); }
    public Symbol[] spin() { return spin.clone(); }
}
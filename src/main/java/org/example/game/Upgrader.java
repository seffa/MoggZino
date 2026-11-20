package org.example.game;

import org.example.Phrases;

import java.util.Locale;
import java.util.Random;

public class Upgrader extends QuickGame {
    private static final double HOUSE_EDGE = 0.05;
    private static final int ROLL_RANGE = 10000;

    private final int currentBalance;
    private final int target;
    private final int roll;

    private int bet;
    private boolean win;
    private double multiplier;

    public Upgrader(Random random, int currentBalance, int target) {
        this(random, currentBalance, target, random.nextInt(ROLL_RANGE));
    }

    public Upgrader(Random random, int currentBalance, int target, int forcedRoll) {
        super(random);
        this.currentBalance = currentBalance;
        this.target = target;
        this.roll = forcedRoll;
    }

    @Override public String id() { return "upgrader"; }
    @Override public String rules() { return new Phrases().GetUpgrader(); }


    public static double calculateMultiplier(int currentBalance, int target, int bet) {
        return 1.0 + (double) (target - currentBalance) / bet;
    }

    public static double calculateChance(double multiplier) {
        return (1 - HOUSE_EDGE) / multiplier;
    }

    public static boolean isWin(int roll, double chance) {
        return roll < chance * ROLL_RANGE;
    }

    @Override
    public int play(int bet) {
        this.bet = bet;
        this.multiplier = calculateMultiplier(currentBalance, target, bet);
        double chance = calculateChance(multiplier);
        this.win = isWin(roll, chance);

        if (win) return target - currentBalance;
        return -bet;
    }

    @Override
    public String result() {
        double chance = calculateChance(multiplier);

        String res = "Цель: " + target + ", Ставка: " + bet + "\n" +
                "Множитель: x" + String.format(Locale.US, "%.2f", multiplier) + "\n" +
                "Шанс: " + String.format(Locale.US, "%.1f", chance * 100) + "%\n\n";

        res += win ? "УСПЕХ! Ты достиг цели." : "ПРОВАЛ. Ставка сгорела.";
        return res;
    }

}
package org.example.game;

import org.example.Phrases;
import java.util.Random;

public class RussianRoulette extends QuickGame {
    private final int bullets;
    private final int firedChamber;
    private int bet;
    private boolean win;

    public RussianRoulette(Random random) {
        super(random);
        this.bullets = random.nextInt(5) + 1;
        this.firedChamber = random.nextInt(6);
    }

    public RussianRoulette(Random random, int bullets, int firedChamber) {
        super(random);
        this.bullets = bullets;
        this.firedChamber = firedChamber;
    }

    @Override public String id() { return "russian_roulette"; }
    @Override public String rules() { return new Phrases().GetRussianRoulette(); }

    @Override
    public int play(int bet) {
        this.bet = bet;

        this.win = firedChamber >= bullets;

        if (win) {
            return bet / 2;
        } else {
            return -bet;
        }
    }

    @Override
    public String result() {
        String res = "В барабане было патронов: " + bullets + "\n";

        if (win) {
            res += "Курок щелкнул вхолостую. Ты выжил!\n";
            res += "Выигрыш: +" + bet/2;
        } else {
            res += "💥 Выстрел! Тебе не повезло.\n";
            res += "Потеряно: -" + bet;
        }

        return res;
    }
}
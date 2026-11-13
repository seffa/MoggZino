package org.example;

import java.util.Random;

public class Bones {
    private int d1, d2;
    private Random random = new Random();

    public Bones() {
        d1 = getDice();
        d2 = getDice();
    }

    private int getDice() {
        return random.nextInt(6) + 1;
    }

    public int game(int dep, int bet) {
        int sum = d1 + d2;
        if (sum == 7 || sum == 11) {
            return dep + bet * 3;
        } else if (sum == 2 || sum == 12) {
            return dep - bet;
        } else {
            return dep;
        }
    }

    public String getResult() {
        return "🎲 " + d1 + " + " + d2 + " = " + (d1 + d2);
    }
}
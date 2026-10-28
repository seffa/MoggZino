package org.example;

import java.util.Random;

public class Bones {
    private int dice1, dice2;
    private Random random = new Random();

    public Bones() {
        dice1 = rollDice();
        dice2 = rollDice();
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    public int game(int deposit, int bet) {
        int sum = dice1 + dice2;

        // 7 или 11 - выигрыш x3
        // 2 или 12 - проигрыш (ставка уходит)
        // Остальное - возврат ставки
        if (sum == 7 || sum == 11) {
            return deposit + bet * 3;
        } else if (sum == 2 || sum == 12) {
            return deposit - bet;
        } else {
            return deposit;
        }
    }

    public String getResult() {
        return "🎲 " + dice1 + " + " + dice2 + " = " + (dice1 + dice2);
    }
}
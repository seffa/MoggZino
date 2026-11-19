package org.example.LV2;

import org.example.Phrases;
import org.example.game.Color;
import org.example.game.QuickGame;
import org.example.game.Roulette;

import java.util.Random;

public class Level2 {
    private final Phrases phrases = new Phrases();
    private final Random random = new Random();
    private int deposit;

    public Level2(int deposit) { this.deposit = deposit; }

    public int GetDeposit() { return deposit; }

    public boolean IsValidBet(int bet) { return bet > 0 && bet <= deposit; }

    public String Game_level2(String id, int bet, String betType) {
        if (!IsValidBet(bet)) return phrases.GetError();
        if (id.equals("russian_roulette")) return "пока не готова";
        if (!id.equals("roulette")) return phrases.GetError();

        Color color = Color.fromText(betType);
        if (color == null) return phrases.GetError();

        QuickGame game = new Roulette(random, color);
        deposit += game.play(bet);
        return game.result() + "\nтвой депозит: " + deposit;
    }
}
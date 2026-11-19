package org.example.LV1;

import org.example.Phrases;
import org.example.game.Bones;
import org.example.game.QuickGame;
import org.example.game.Slots;

import java.util.Random;

public class Level1 {
    private final Phrases phrases = new Phrases();
    private final Random random = new Random();
    private int deposit = 100;

    public int GetDeposit() { return deposit; }

    public boolean IsValidBet(int bet) { return bet > 0 && bet <= deposit; }

    public String Game_level1(String id, int bet) {
        if (!IsValidBet(bet)) return phrases.GetError();

        QuickGame game = switch (id) {
            case "bones" -> new Bones(random);
            case "slots" -> new Slots(random);
            default -> null;
        };
        if (game == null) return phrases.GetError();

        deposit += game.play(bet);
        return game.result() + "\nтвой депозит: " + deposit;
    }
}
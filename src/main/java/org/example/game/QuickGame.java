package org.example.game;

import java.util.Random;

public abstract class QuickGame extends Game {
    protected QuickGame(Random random) {
        super(random);
    }

    public abstract int play(int bet);
    public abstract String result();
}
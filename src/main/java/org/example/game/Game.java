package org.example.game;

import java.util.Random;

public abstract class Game {
    protected final Random random;

    protected Game(Random random) {
        this.random = random;
    }

    public abstract String id();
    public abstract String rules();

    public int betStep() {
        return 1;
    }
}
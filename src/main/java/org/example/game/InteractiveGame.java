package org.example.game;

import java.util.Random;

public abstract class InteractiveGame extends Game {
    protected InteractiveGame(Random random) { super(random); }

    public abstract void start(int bet);
    public abstract void hit();
    public abstract void stand();
    public abstract boolean isFinished();
    public abstract int getDelta();
    public abstract String state();
}
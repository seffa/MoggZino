package org.example.player;

import org.example.game.InteractiveGame;
import org.example.hall.Level;
import org.example.hall.Levels;

import java.util.Random;

public class Player {
    public static final int START_DEPOSIT = 100;

    private final long userId;
    private final Random random;
    private int deposit = START_DEPOSIT;
    private Level hall;
    private String waiting;
    private String option;
    private boolean apology;
    private InteractiveGame activeGame;


    public Player(long userId, Random random) {
        this.userId = userId;
        this.random = random;
        hall = Levels.create(1, random);
    }

    public long userId() { return userId; }
    public int deposit() { return deposit; }
    public int level() { return hall.number(); }
    public Level hall() { return hall; }
    public String waiting() { return waiting; }
    public String option() { return option; }
    public boolean apologySent() { return apology; }

    public boolean broke() { return deposit == 0; }
    public boolean canBet(int bet) { return !broke() && bet > 0 && bet <= deposit; }
    public boolean canReset() { return broke() && apology; }

    public InteractiveGame getActiveGame() { return activeGame; }
    public void setActiveGame(InteractiveGame game) { this.activeGame = game; }
    public void clearActiveGame() { this.activeGame = null; }

    public void wait(String gameId) { wait(gameId, null); }

    public void wait(String gameId, String gameOption) {
        waiting = gameId;
        option = gameOption;
    }

    public void stopWaiting() {
        waiting = null;
        option = null;
    }

    public void apply(int delta) {
        deposit = Math.max(0, deposit + delta);
        stopWaiting();
        if (broke()) apology = false;
        promote();
    }

    public boolean apologize() {
        if (!broke()) return false;
        apology = true;
        return true;
    }

    public boolean reset() {
        if (!canReset()) return false;
        deposit = START_DEPOSIT;
        hall = Levels.create(1, random);
        stopWaiting();
        apology = false;
        activeGame = null;
        return true;
    }

    private void promote() {
        Level next = Levels.create(hall.number() + 1, random);
        while (next != null && deposit >= next.threshold()) {
            hall = next;
            next = Levels.create(hall.number() + 1, random);
        }
    }
}
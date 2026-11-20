package org.example.hall;

import org.example.game.Game;
import java.util.List;

public abstract class Level {
    public abstract int number();
    public abstract int threshold();
    public abstract List<String> ids();

    public abstract Game create(String id, String option, int balance);
    public boolean has(String id) { return ids().contains(id); }
    public List<String> options(String id) { return List.of(); }
}
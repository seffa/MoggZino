package org.example.hall;

import org.example.game.QuickGame;

import java.util.List;

public abstract class Level {
    public abstract int number();
    public abstract int threshold();
    public abstract List<String> ids();
    public abstract QuickGame create(String id, String option);

    public QuickGame create(String id) { return create(id, null); }
    public boolean has(String id) { return ids().contains(id); }
    public List<String> options(String id) { return List.of(); }
}
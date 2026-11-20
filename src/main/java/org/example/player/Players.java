package org.example.player;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Players {
    private final Map<Long, Player> players = new ConcurrentHashMap<>();
    private final Random random;

    public Players() { this(new Random()); }

    public Players(Random random) { this.random = random; }

    public Player get(long userId) {
        return players.computeIfAbsent(userId, id -> new Player(id, random));
    }

    public int count() { return players.size(); }
}
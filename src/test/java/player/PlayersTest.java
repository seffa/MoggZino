package org.example.player;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    @Test
    void sameUserGetsSamePlayer() {
        Players players = new Players();
        assertSame(players.get(1L), players.get(1L));
    }

    @Test
    void differentUsersGetDifferentPlayers() {
        Players players = new Players();
        assertNotSame(players.get(1L), players.get(2L));
    }

    @Test
    void newUserStartsWithHundred() {
        assertEquals(100, new Players().get(42L).deposit());
    }

    @Test
    void defeatedPlayerIsNotRecreated() {
        Players players = new Players();
        Player player = players.get(42L);
        player.apply(-100);

        Player again = players.get(42L);
        assertSame(player, again);
        assertEquals(0, again.deposit());
        assertTrue(again.broke());
    }

    @Test
    void countGrowsWithNewUsers() {
        Players players = new Players();
        players.get(1L);
        players.get(2L);
        players.get(1L);
        assertEquals(2, players.count());
    }

    @Test
    void concurrentAccessCreatesSinglePlayer() throws Exception {
        Players players = new Players();
        int threads = 16;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch start = new CountDownLatch(1);
        Set<Player> found = ConcurrentHashMap.newKeySet();
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            futures.add(pool.submit(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                found.add(players.get(42L));
            }));
        }
        start.countDown();
        for (Future<?> future : futures) future.get(5, TimeUnit.SECONDS);
        pool.shutdownNow();

        assertEquals(1, found.size());
    }
}
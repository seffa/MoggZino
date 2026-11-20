package org.example.player;

import org.example.game.QuickGame;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player() { return new Player(1L, new Random(7)); }

    @Test
    void startsWithHundred() { assertEquals(100, player().deposit()); }

    @Test
    void promotesToSecondHallAtTwoThousand() {
        Player player = player();
        player.apply(1900);
        assertEquals(2, player.level());
        assertTrue(player.hall().has("roulette"));
        assertFalse(player.hall().has("bones"));
    }

    @Test
    void promotesToThirdHallAtFiveThousand() {
        Player player = player();
        player.apply(4900);
        assertEquals(3, player.level());
        assertTrue(player.hall().has("slots"));
    }

    @Test
    void jumpsOverHallsOnBigWin() {
        Player player = player();
        player.apply(9900);
        assertEquals(10000, player.deposit());
        assertEquals(3, player.level());
    }

    @Test
    void hallDoesNotDowngrade() {
        Player player = player();
        player.apply(1900);
        player.apply(-1500);
        assertEquals(500, player.deposit());
        assertEquals(2, player.level());
    }

    @Test
    void cannotResetWithPositiveDeposit() { assertFalse(player().reset()); }

    @Test
    void cannotResetWithoutApology() {
        Player player = player();
        player.apply(-100);
        assertFalse(player.canReset());
        assertFalse(player.reset());
        assertEquals(0, player.deposit());
    }

    @Test
    void apologyRejectedWhenNotBroke() {
        Player player = player();
        assertFalse(player.apologize());
        assertFalse(player.apologySent());
    }

    @Test
    void resetWorksAfterApology() {
        Player player = player();
        player.apply(-100);
        assertTrue(player.apologize());
        assertTrue(player.canReset());
        assertTrue(player.reset());
        assertEquals(100, player.deposit());
        assertFalse(player.broke());
    }

    @Test
    void resetClearsApology() {
        Player player = player();
        player.apply(-100);
        player.apologize();
        player.reset();
        assertFalse(player.apologySent());
    }

    @Test
    void newLossRequiresNewApology() {
        Player player = player();
        player.apply(-100);
        player.apologize();
        player.reset();
        player.apply(-100);
        assertFalse(player.apologySent());
        assertFalse(player.canReset());
    }

    @Test
    void resetReturnsToFirstHall() {
        Player player = player();
        player.apply(4900);
        player.reset();
        assertEquals(1, player.level());
        assertEquals(100, player.deposit());
    }

    @Test
    void optionStoredWithGame() {
        Player player = player();
        player.wait("roulette", "красное");
        assertEquals("красное", player.option());
        player.stopWaiting();
        assertNull(player.option());
    }

    @Test
    void startsInFirstHall() {
        Player player = player();
        assertEquals(1, player.level());
        assertEquals(1, player.hall().number());
    }

    @Test
    void applyAddsDelta() {
        Player player = player();
        player.apply(30);
        assertEquals(130, player.deposit());
    }

    @Test
    void applySubtractsDelta() {
        Player player = player();
        player.apply(-10);
        assertEquals(90, player.deposit());
    }

    @Test
    void zeroDepositMeansBroke() {
        Player player = player();
        player.apply(-100);
        assertTrue(player.broke());
        assertEquals(0, player.deposit());
    }

    @Test
    void brokePlayerCannotBet() {
        Player player = player();
        player.apply(-100);
        assertFalse(player.canBet(1));
        assertFalse(player.canBet(10));
    }

    @Test
    void betAboveDepositRejected() { assertFalse(player().canBet(101)); }

    @Test
    void zeroBetRejected() { assertFalse(player().canBet(0)); }

    @Test
    void negativeBetRejected() { assertFalse(player().canBet(-5)); }

    @Test
    void validBetAccepted() {
        Player player = player();
        assertTrue(player.canBet(1));
        assertTrue(player.canBet(100));
    }

    @Test
    void waitStoresGameId() {
        Player player = player();
        player.wait("bones");
        assertEquals("bones", player.waiting());
        player.stopWaiting();
        assertNull(player.waiting());
    }

    @Test
    void applyClearsWaiting() {
        Player player = player();
        player.wait("slots");
        player.apply(10);
        assertNull(player.waiting());
    }

    @Test
    void resetRestoresDeposit() {
        Player player = player();
        player.apply(-100);
        player.reset();
        assertEquals(100, player.deposit());
        assertFalse(player.broke());
        assertEquals(1, player.level());
    }

    @Test
    void resetClearsWaiting() {
        Player player = player();
        player.wait("bones");
        player.reset();
        assertNull(player.waiting());
    }

    @Test
    void defeatedPlayerStaysAtZero() {
        Player player = player();
        player.apply(-100);
        player.apply(-50);
        assertEquals(0, player.deposit());
        assertTrue(player.broke());
    }

    @Test
    void depositNeverGoesNegative() {
        Player player = player();
        for (int i = 0; i < 500 && !player.broke(); i++) {
            int bet = Math.min(10, player.deposit());
            QuickGame game = player.hall().create(i % 2 == 0 ? "bones" : "slots");
            player.apply(game.play(bet));
            assertTrue(player.deposit() >= 0);
        }
    }
}
package org.example.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {
    private Blackjack gameWithDeck(Integer... cards) {
        List<Integer> deck = new ArrayList<>(Arrays.asList(cards));
        return new Blackjack(new Random(1), new ArrayList<>(), new ArrayList<>(), deck);
    }


    @Test
    void aceCountsAsEleven() {
        assertEquals(21, Blackjack.score(List.of(1, 10)));
    }

    @Test
    void multipleAcesAdjustToAvoidBust() {
        assertEquals(21, Blackjack.score(List.of(1, 1, 9)));
        assertEquals(21, Blackjack.score(List.of(1, 1, 1, 8)));
    }

    @Test
    void faceCardsCountAsTen() {
        assertEquals(30, Blackjack.score(List.of(11, 12, 13)));
    }

    @Test
    void playerNaturalBlackjackPays3To2() {
        // Игроку: Туз, 10 (21). Дилеру: 10, 8 (18)
        Blackjack bj = gameWithDeck(1, 10, 10, 8);
        bj.start(100);

        assertTrue(bj.isFinished());
        assertEquals(150, bj.getDelta());
        assertTrue(bj.state().contains("Блэкджек"));
    }

    @Test
    void dealerNaturalBlackjackBeatsPlayer() {
        Blackjack bj = gameWithDeck(10, 1, 8, 10);
        bj.start(100);

        assertTrue(bj.isFinished());
        assertEquals(-100, bj.getDelta());
    }

    @Test
    void playerBustsImmediatelyOnHit() {
        Blackjack bj = gameWithDeck(10, 10, 6, 8, 10);
        bj.start(100);
        assertFalse(bj.isFinished());

        bj.hit();

        assertTrue(bj.isFinished());
        assertEquals(-100, bj.getDelta());
        assertTrue(bj.state().contains("Перебор"));
    }

    @Test
    void dealerDrawsUntilSeventeen() {
        Blackjack bj = gameWithDeck(10, 10, 8, 6, 2);
        bj.start(100);

        bj.stand();

        assertTrue(bj.isFinished());
        assertEquals(0, bj.getDelta());
        assertTrue(bj.state().contains("Ничья"));
    }

    @Test
    void dealerBustsAndPlayerWins() {
        Blackjack bj = gameWithDeck(10, 10, 8, 6, 10);
        bj.start(100);

        bj.stand();

        assertTrue(bj.isFinished());
        assertEquals(100, bj.getDelta());
        assertTrue(bj.state().contains("перебор"));
    }


    @Test
    void dealerFirstCardIsHiddenDuringGame() {
        Blackjack bj = gameWithDeck(10, 10, 8, 6);
        bj.start(100);

        String view = bj.state();
        assertTrue(view.contains("Карта дилера: 10 [?]"));
        assertFalse(view.contains("Карта дилера: 10, 6"));
    }

    @Test
    void dealerHandIsRevealedAtTheEnd() {
        Blackjack bj = gameWithDeck(10, 10, 8, 6, 10);
        bj.start(100);
        bj.stand();

        String view = bj.state();
        assertTrue(view.contains("Карты дилера: 10, 6, 10"));
    }
}
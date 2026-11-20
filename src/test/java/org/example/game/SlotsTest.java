package org.example.game;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import static org.example.game.Symbol.*;
import static org.junit.jupiter.api.Assertions.*;

class SlotsTest {

    private Symbol[] spin(Symbol a, Symbol b, Symbol c) { return new Symbol[]{a, b, c}; }

    @Test
    void cherriesPayDouble() { assertEquals(20, Slots.payout(spin(CHERRY, CHERRY, CHERRY), 10)); }

    @Test
    void lemonsPayTriple() { assertEquals(30, Slots.payout(spin(LEMON, LEMON, LEMON), 10)); }

    @Test
    void barsPayFive() { assertEquals(50, Slots.payout(spin(BAR, BAR, BAR), 10)); }

    @Test
    void sevensPayTen() { assertEquals(100, Slots.payout(spin(SEVEN, SEVEN, SEVEN), 10)); }

    @Test
    void twoSameReturnBet() { assertEquals(0, Slots.payout(spin(CHERRY, CHERRY, LEMON), 10)); }

    @Test
    void twoSameInAnyPosition() { assertEquals(0, Slots.payout(spin(BAR, CHERRY, BAR), 10)); }

    @Test
    void allDifferentLose() { assertEquals(-10, Slots.payout(spin(CHERRY, LEMON, BAR), 10)); }

    @Test
    void weightsSumToHundred() {
        int sum = 0;
        for (Symbol symbol : Symbol.values()) sum += symbol.weight();
        assertEquals(100, sum);
    }

    @Test
    void frequenciesMatchWeights() {
        Random random = new Random(1);
        Map<Symbol, Integer> hits = new EnumMap<>(Symbol.class);
        int rounds = 100000;
        for (int i = 0; i < rounds; i++) {
            Symbol symbol = Symbol.pick(random);
            hits.merge(symbol, 1, Integer::sum);
        }
        for (Symbol symbol : Symbol.values()) {
            double share = 100.0 * hits.getOrDefault(symbol, 0) / rounds;
            assertTrue(Math.abs(share - symbol.weight()) < 1.5, symbol + " выпадает " + share + "%");
        }
    }

    @Test
    void textHasSeparators() { assertEquals("🍒 | 🍋 | BAR", Slots.text(spin(CHERRY, LEMON, BAR))); }

    @Test
    void houseEdgeStaysNearFourPercent() {
        Random random = new Random(7);
        int rounds = 200000;
        long total = 0;
        for (int i = 0; i < rounds; i++) total += new Slots(random).play(10);
        double rtp = 1 + (double) total / (rounds * 10);
        assertTrue(rtp > 0.95 && rtp < 0.968, "RTP слотов уехал: " + rtp);
    }
}
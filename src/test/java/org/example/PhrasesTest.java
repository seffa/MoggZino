package org.example;

import org.example.game.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhrasesTest {

    private final Phrases phrases = new Phrases();

    @Test
    void allTextsAreFilled() {
        List<String> texts = List.of(
                phrases.GetHelp(), phrases.GetGreeting(), phrases.GetRulesLevel2(), phrases.GetRulesLevel3(),
                phrases.GetRulesBones(), phrases.GetSlots(), phrases.GetRoulette(), phrases.GetRussianRoulette(),
                phrases.GetBlackjack(), phrases.GetUpgrader(), phrases.GetError(), phrases.GetDefeat(), phrases.GetWin());
        for (String text : texts) {
            assertNotNull(text);
            assertFalse(text.isBlank());
        }
    }

    @Test
    void greetingHasDepositPlaceholder() { assertTrue(phrases.GetGreeting().contains("{deposit}")); }

    @Test
    void winHasDepositPlaceholder() { assertTrue(phrases.GetWin().contains("{deposit}")); }

    @Test
    void defeatMentionsZeroDeposit() { assertTrue(phrases.GetDefeat().contains("ДЕПОЗИТ = 0")); }

    @Test
    void helpMentionsButtons() { assertTrue(phrases.GetHelp().toLowerCase().contains("кнопк")); }

    @Test
    void slotsRulesListEverySymbol() {
        for (Symbol symbol : Symbol.values()) {
            assertTrue(phrases.GetSlots().contains(symbol.label()), "в правилах нет символа " + symbol);
        }
    }
}
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhrasesTest {
    private Phrases phrases;

    @BeforeEach
    void setUp() {
        phrases = new Phrases();
    }

    @Test
    void testGetHelp() {
        String help = phrases.GetHelp();

        assertNotNull(help);
        assertTrue(help.contains("ПРАВИЛА УРОВНЕЙ"));
        assertTrue(help.contains("\\help"));
    }

    @Test
    void testGetGreeting() {
        String greeting = phrases.GetGreeting();

        assertNotNull(greeting);
        assertTrue(greeting.contains("Добро пожаловать"));
    }

    @Test
    void testGetRulesLevel1() {
        String rules = phrases.GetRulesLevel1();

        assertNotNull(rules);
        assertTrue(rules.contains("ДОСТУПНЫЕ ИГРЫ"));
        assertTrue(rules.contains("кости"));
        assertTrue(rules.contains("слоты"));
    }

    @Test
    void testGetSlots() {
        String slots = phrases.GetSlots();

        assertNotNull(slots);
        assertTrue(slots.contains("СЛОТЫ"));
    }

    @Test
    void testGetBones() {
        String bones = phrases.GetRulesBones();

        assertNotNull(bones);
        assertTrue(bones.contains("КОСТИ"));
    }

    @Test
    void testGetError() {
        String error = phrases.GetError();

        assertNotNull(error);
        assertTrue(error.contains("ошибка"));
    }

    @Test
    void testGetDefeat() {
        String defeat = phrases.GetDefeat();

        assertNotNull(defeat);
        assertTrue(defeat.contains("ДЕПОЗИТ = 0"));
    }

    @Test
    void testGetWin() {
        String win = phrases.GetWin();

        assertNotNull(win);
        assertTrue(win.contains("ПОБЕДА"));
    }
}
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhrasesTest {

    @Test
    void testGetHelp() {
        Phrases p = new Phrases();
        String h = p.GetHelp();
        assertNotNull(h);
        assertTrue(h.contains("ПРАВИЛА УРОВНЕЙ"));
        assertTrue(h.contains("\\help"));
    }

    @Test
    void testGetGreeting() {
        Phrases p = new Phrases();
        String g = p.GetGreeting();
        assertNotNull(g);
        assertTrue(g.contains("Добро пожаловать"));
    }

    @Test
    void testGetRulesLevel1() {
        Phrases p = new Phrases();
        String r = p.GetRulesLevel1();
        assertNotNull(r);
        assertTrue(r.contains("ДОСТУПНЫЕ ИГРЫ"));
        assertTrue(r.contains("кости"));
        assertTrue(r.contains("слоты"));
    }

    @Test
    void testGetSlots() {
        Phrases p = new Phrases();
        String s = p.GetSlots();
        assertNotNull(s);
        assertTrue(s.contains("СЛОТЫ"));
    }

    @Test
    void testGetBones() {
        Phrases p = new Phrases();
        String b = p.GetRulesBones();
        assertNotNull(b);
        assertTrue(b.contains("КОСТИ"));
    }

    @Test
    void testGetError() {
        Phrases p = new Phrases();
        String e = p.GetError();
        assertNotNull(e);
        assertTrue(e.contains("ошибка"));
    }

    @Test
    void testGetDefeat() {
        Phrases p = new Phrases();
        String d = p.GetDefeat();
        assertNotNull(d);
        assertTrue(d.contains("ДЕПОЗИТ = 0"));
    }

    @Test
    void testGetWin() {
        Phrases p = new Phrases();
        String w = p.GetWin();
        assertNotNull(w);
        assertTrue(w.contains("ПОБЕДА"));
    }
}
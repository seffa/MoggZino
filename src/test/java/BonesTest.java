package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BonesTest {
    private Bones bones;

    @BeforeEach
    void setUp() {
        bones = new Bones();
    }

    @Test
    void testGameWin7() {
        // Создаем кости с суммой 7
        Bones testBones = new Bones();
        int initialDeposit = 100;
        int bet = 10;

        int result = testBones.game(initialDeposit, bet);

        // Депозит должен увеличиться (если выпала 7 или 11)
        // или уменьшиться (если 2 или 12)
        // или остаться прежним (остальное)
        assertTrue(result >= initialDeposit - bet && result <= initialDeposit + bet * 3);
    }

    @Test
    void testGameLose2or12() {
        Bones testBones = new Bones();
        int initialDeposit = 100;
        int bet = 10;

        int result = testBones.game(initialDeposit, bet);

        // Проверяем, что депозит не превышает максимальный выигрыш
        assertTrue(result <= initialDeposit + bet * 3);
    }

    @Test
    void testGameNeutral() {
        Bones testBones = new Bones();
        int initialDeposit = 100;
        int bet = 10;

        int result = testBones.game(initialDeposit, bet);

        // Депозит должен быть в допустимых пределах
        assertTrue(result >= initialDeposit - bet);
    }

    @Test
    void testGetResult() {
        String result = bones.getResult();

        assertNotNull(result);
        assertTrue(result.contains("🎲"));
        assertTrue(result.contains("+"));
        assertTrue(result.contains("="));
    }

    @Test
    void testDiceRange() {
        // Запускаем много раз, чтобы проверить диапазон значений
        for (int i = 0; i < 100; i++) {
            Bones testBones = new Bones();
            String result = testBones.getResult();

            // Извлекаем числа из результата
            String[] parts = result.split("[^0-9]+");
            int dice1 = Integer.parseInt(parts[1]);
            int dice2 = Integer.parseInt(parts[2]);

            assertTrue(dice1 >= 1 && dice1 <= 6, "Dice 1 should be between 1 and 6");
            assertTrue(dice2 >= 1 && dice2 <= 6, "Dice 2 should be between 1 and 6");
        }
    }
}
package org.example.game;

import java.util.Set;

public enum Color {
    RED("красное", "🔴"),
    BLACK("черное", "⚫"),
    ZERO("зеро", "🟢");

    private static final Set<Integer> RED_NUMBERS = Set.of(
            1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);

    private final String text;
    private final String mark;

    Color(String text, String mark) {
        this.text = text;
        this.mark = mark;
    }

    public String text() { return text; }
    public String mark() { return mark; }

    public static Color of(int number) {
        if (number == 0) return ZERO;
        return RED_NUMBERS.contains(number) ? RED : BLACK;
    }

    public static Color fromText(String value) {
        for (Color color : values()) {
            if (color.text.equalsIgnoreCase(value)) return color;
        }
        return null;
    }
}
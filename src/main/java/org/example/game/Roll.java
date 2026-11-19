package org.example.game;

public record Roll(int first, int second) {
    public int sum() { return first + second; }
}
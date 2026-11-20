package org.example.game;

import org.example.Phrases;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Blackjack extends InteractiveGame {
    private final List<Integer> deck = new ArrayList<>();
    private final List<Integer> playerHand = new ArrayList<>();
    private final List<Integer> dealerHand = new ArrayList<>();
    private int bet;
    private int delta;
    private boolean finished;
    private String message;

    public Blackjack(Random random) {
        super(random);
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) deck.add(i);
        }
        Collections.shuffle(deck, random);
    }

    public Blackjack(Random random, List<Integer> playerHand, List<Integer> dealerHand, List<Integer> deck) {
        super(random);
        this.playerHand.addAll(playerHand);
        this.dealerHand.addAll(dealerHand);
        this.deck.addAll(deck);
    }

    @Override
    public void start(int bet) {
        this.bet = bet;
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));

        int pScore = score(playerHand);
        int dScore = score(dealerHand);

        if (pScore == 21 && dScore == 21) {
            message = "У обоих Блэкджек! Ничья.";
            delta = 0;
            finished = true;
        } else if (pScore == 21) {
            message = "У тебя Блэкджек! Выплата 3:2.";
            delta = (int)(bet * 1.5);
            finished = true;
        } else if (dScore == 21) {
            message = "У дилера Блэкджек. Ты проиграл.";
            delta = -bet;
            finished = true;
        } else {
            message = "Твой ход. Взять карту или Хватит?";
            finished = false;
        }
    }

    @Override
    public void hit() {
        if (finished) return;
        playerHand.add(deck.remove(0));
        if (score(playerHand) > 21) {
            message = "Перебор! Ты проиграл.";
            delta = -bet;
            finished = true;
        }
    }

    @Override
    public void stand() {
        if (finished) return;
        while (score(dealerHand) < 17) {
            dealerHand.add(deck.remove(0));
        }
        int p = score(playerHand);
        int d = score(dealerHand);

        if (d > 21) {
            message = "У дилера перебор (" + d + "). Ты выиграл!";
            delta = bet;
        } else if (p > d) {
            message = "У тебя больше очков. Ты выиграл!";
            delta = bet;
        } else if (p < d) {
            message = "У дилера больше очков. Ты проиграл.";
            delta = -bet;
        } else {
            message = "Ничья. Ставка возвращается.";
            delta = 0;
        }
        finished = true;
    }

    @Override public boolean isFinished() { return finished; }
    @Override public int getDelta() { return delta; }
    @Override public String id() { return "blackjack"; }
    @Override public String rules() { return new Phrases().GetBlackjack(); }

    @Override
    public String state() {
        StringBuilder sb = new StringBuilder();
        sb.append("Твои карты: ").append(formatHand(playerHand)).append(" (Очки: ").append(score(playerHand)).append(")\n");

        if (finished) {
            sb.append("Карты дилера: ").append(formatHand(dealerHand)).append(" (Очки: ").append(score(dealerHand)).append(")\n\n");
            sb.append(message);
        } else {
            sb.append("Карта дилера: ").append(cardName(dealerHand.get(0))).append(" [?]\n\n");
            sb.append(message);
        }
        return sb.toString();
    }

    public static int score(List<Integer> hand) {
        int sum = 0;
        int aces = 0;
        for (int card : hand) {
            if (card == 1) { aces++; sum += 11; }
            else if (card > 10) sum += 10;
            else sum += card;
        }
        while (sum > 21 && aces > 0) {
            sum -= 10;
            aces--;
        }
        return sum;
    }

    private String formatHand(List<Integer> hand) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            sb.append(cardName(hand.get(i)));
            if (i < hand.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    private String cardName(int card) {
        return switch (card) {
            case 1 -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(card);
        };
    }
}
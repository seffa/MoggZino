package org.example.LV1;

import org.example.Phrases;

import java.util.Random;

public class Slots {

    private static final int reels = 3; //кол-во барабанов


    private Random random = new Random();
    private String[] spin = new String[reels];

    public Slots() {
        for (int i = 0; i < reels; i++) {
            spin[i] = getRandomSymbol();
        }
    }

    private String getRandomSymbol() {
        int roll = random.nextInt(100);
        if (roll < 40) return "\uD83C\uDF52";
        if (roll < 70) return "\uD83C\uDF4B";
        if (roll < 90) return "BAR";
        return "7";
    }

    public int game(int depos, int bet) {
        int max = 1;
        for (int i = 0; i < reels; i++) {
            int count = 0;
            for (int j = 0; j < reels; j++) {
                if (spin[i].equals(spin[j])){
                    count++;
                }
            }
            if (count > max){
                max = count;
            }
        }

        if (max == reels) {
            if (spin[0].equals("\uD83C\uDF52")){
                return depos + bet * 2;
            }
            if (spin[0].equals("\uD83C\uDF4B")) {
                return depos + bet * 3;
            }
            if (spin[0].equals("BAR")){
                return depos + bet * 5;
            }
            return depos + bet * 10;
        } else if (max >= 2) {
            return depos;
        } else {
            return depos - bet;
        }
    }

    public String getResult() {
        String s = "";
        for (int i = 0; i < reels; i++) {
            if (i > 0) {
                s += " | ";
            }
            s += spin[i];
        }
        return s;
    }
}
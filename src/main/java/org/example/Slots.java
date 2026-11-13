package org.example;

import java.util.Random;

public class Slots {
    private String[] symbols;
    private Random random = new Random();
    private String a, b, c;

    public Slots() {
        symbols = new String[] {"BAR", "7", "\uD83C\uDF52", "\uD83C\uDF4B"};
        a = getRandomSymbol();
        b = getRandomSymbol();
        c = getRandomSymbol();
    }

    public String Start(){
        Phrases slot = new Phrases();
        return slot.GetSlots();
    }

    private String getRandomSymbol() {
        int roll = random.nextInt(100);

        if (roll < 40) {
            return "\uD83C\uDF52";
        } else if (roll < 70) {
            return "\uD83C\uDF4B";
        } else if (roll < 90) {
            return "BAR";
        } else {
            return "7";
        }
    }

    public int game(int depos, int bet){
        if(a == b && b == c){
            if (a == "\uD83C\uDF52") {
                bet = bet * 2;
            }else if (a=="\uD83C\uDF4B"){
                bet = bet * 3;
            }else if(a == "BAR"){
                bet = bet * 5;
            } else{
                bet = bet * 10;
            }
            return depos + bet;
        } else if(a==b || b==c || a==c){
            return depos;
        }
        else {
            return depos - bet;
        }

    }

    public String getResult(){ return a + " | " + b + " | " + c;}
}
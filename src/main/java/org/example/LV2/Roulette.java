package org.example.LV2;

import java.util.Random;

public class Roulette {
    private Random random = new Random();
    private static final int[] RED_NUMBERS = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };
    private String field;

    public Roulette(){
        field = getColor();
    }

    private boolean isRed(int n) {
        for (int r : RED_NUMBERS) {
            if (r == n) return true;
        }
        return false;
    }

    private String getColor(){
        int SpinNumber = random.nextInt(37);
        if (SpinNumber == 0) return "зеро";
        else if (isRed(SpinNumber)) return "красное";
        else return "черное";
    }

    public int game(int dep, int bet, String betType){
        if (field.equals("красное") && betType.equals("красное")) return dep+bet;
        else if (field.equals("черное") && betType.equals("черное")) return dep+bet;
        else if (field.equals("зеро") && betType.equals("зеро")) return dep + bet*35;
        else return dep - bet;
    }

    public String getResult(){
        if (field.equals("красное")) return "\uD83D\uDD34 выпало красное";
        else if (field.equals("черное")) return "⚫ выпало черное";
        else return "\uD83D\uDFE2 выпало зеро";
    }
}

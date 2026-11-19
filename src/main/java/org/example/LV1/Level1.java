package org.example.LV1;

import org.example.Phrases;

public class Level1 {
    private Phrases P_lv1;
    private Slots S_lv1;
    private Bones B_lv1;
    private int deposit;

    public Level1() {
        P_lv1 = new Phrases();
        deposit = 100;
    }

    public int GetDeposit() {
        return deposit;
    }

    public boolean IsValidBet(int bet) { //в отдельный класс?
        return bet > 0 && bet <= deposit;
    }

    public String Game_level1(String s, int b) {
        if (!IsValidBet(b)){
            return P_lv1.GetError();
        }

        String ss;
        if (s.equals("bones")) {
            B_lv1 = new Bones();
            deposit = B_lv1.game(deposit, b);
            ss = B_lv1.getResult();
        } else if (s.equals("slots")) {
            S_lv1 = new Slots();
            deposit = S_lv1.game(deposit, b);
            ss = S_lv1.getResult();
        } else {
            return P_lv1.GetError();
        }

        return ss + "\nтвой депозит: " + deposit;
    }
}
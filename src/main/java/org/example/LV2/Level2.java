package org.example.LV2;

import org.example.Phrases;

public class Level2 {
    private Phrases P_lv2;
    private Roulette R_lv2;
    private int deposit;

    public Level2(int dep) {
        P_lv2= new Phrases();
        this.deposit = dep;
    }

    public int GetDeposit() {
        return deposit;
    }

    public boolean IsValidBet(int bet) { //в отдельный класс?
        return bet > 0 && bet <= deposit;
    }

    public String Game_level2(String s, int b, String betType) {
        if (!IsValidBet(b)){
            return P_lv2.GetError();
        }

        String ss;
        if (s.equals("roulette")) {
            R_lv2 = new Roulette();
            deposit = R_lv2.game(deposit, b, betType);
            ss = R_lv2.getResult();
        } else if (s.equals("russian_roulette")) {
            return "пока не готова";
        } else {
            return P_lv2.GetError();
        }

        return ss + "\nтвой депозит: " + deposit;
    }
}
package org.example;

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

    public String lv1_out(String s) {
        String[] str = s.split(" ");
        if (str[0].equals("\\bet")) {
            return Game_level1(str[1], Integer.parseInt(str[2]));
        } else if (str[0].equals("\\slots")) {
            return P_lv1.GetSlots();
        } else if (str[0].equals("\\bones")) {
            return P_lv1.GetRulesBones();
        } else {
            return P_lv1.GetError();
        }
    }

    public String Game_level1(String s, int b) {
        String ss = "";
        if (s.equals("bones")) {
            B_lv1 = new Bones();
            deposit = B_lv1.game(deposit, b);
            ss = B_lv1.getResult();
        } else if (s.equals("slots")) {
            S_lv1 = new Slots();
            deposit = S_lv1.game(deposit, b);
            ss = S_lv1.getResult();
        }
        return ss + "\nтвой депозит: " + Integer.toString(deposit);
    }
}
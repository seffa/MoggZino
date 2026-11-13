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
        String cmd = str[0].toLowerCase();
        if (cmd.startsWith("\\")) {
            cmd = "/" + cmd.substring(1);
        }

        if (cmd.equals("/bet")) {
            if (str.length < 3) {
                return "надо: /bet игра ставка";
            }
            int bet;
            try {
                bet = Integer.parseInt(str[2]);
            } catch (NumberFormatException e) {
                return "ставка должна быть числом";
            }
            return Game_level1(str[1].toLowerCase(), bet);
        } else if (cmd.equals("/slots")) {
            return P_lv1.GetSlots();
        } else if (cmd.equals("/bones")) {
            return P_lv1.GetRulesBones();
        } else if (cmd.equals("/help")) {
            return P_lv1.GetHelp();
        } else if (cmd.equals("/status")) {
            return "твой депозит: " + deposit;
        } else if (cmd.equals("/play") || cmd.equals("/level1")) {
            return P_lv1.GetRulesLevel1();
        } else {
            return P_lv1.GetError();
        }
    }

    public String Game_level1(String s, int b) {
        if (b <= 0) {
            return "ставка должна быть больше 0";
        }
        if (b > deposit) {
            return "недостаточно фишек. твой депозит: " + deposit;
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
            return "нет такой игры";
        }

        return ss + "\nтвой депозит: " + deposit;
    }
}
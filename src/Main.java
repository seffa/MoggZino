import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Phrases P_main = new Phrases();
        Level1 main1 = new Level1();

        while(main1.GetDeposit() < 1000){
            System.out.println(P_main.GetRulesLevel1());

            String s1 = sc.nextLine();
            String[] str = s1.split(" ");

            if(str[0].equals("\\q")) {
                break;
            } else {
                System.out.println(main1.lv1_out(s1));
            }

        }
    }
}

/*else if (str[0].equals("\\bet")){
        System.out.println(main1.Game_level1(str[1], Integer.parseInt(str[2])));
        }else if(str[0].equals("\\slots")){
        System.out.println(P_main.GetSlots());
        }else if(str[0].equals("\\bones")){
        System.out.println(P_main.GetRulesBones());
        }else {
        System.out.println(P_main.GetError());
        }*/
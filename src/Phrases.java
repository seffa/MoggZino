public class Phrases {
    private String Help;
    private String Greeting;
    private String RulesLevel1;
    private String RulesLevel2;
    private String RulesLevel3;
    private String Bones;
    private String Slots;
    private String Roulette;
    private String RussianRoulette;
    private String Blackjack;
    private String Upgrader;
    private String Error;
    private String Defeat;
    private String Win;

    public Phrases(){
        Help = "ПРАВИЛА УРОВНЕЙ" +
                "Для перехода на уровень 1 необходимо набрать n очков \n" +
                "Для перехода на уровень 2 необходимо набрать n+m очков \n" +
                "Для перехода на уровень 3 необходимо набрать n+k очков \n" +
                "Чтобы узнать правила игр на уровне введите \"\\level1\" или \"\\level2\" или \"\\level3\" \n\n" +
                "ДРУГИЕ КОМАНДЫ:\n" +
                "• \\play — открыть список игр на текущем уровне\n" +
                "• \\bet <игра> <ставка> — сделать ставку\n" +
                "• \\status — посмотреть депозит, долг, уровень\n\n" +
                "ДОЛГ\n" +
                "• Долг появляется, когда депозит падает до нуля.\n" +
                "• Долг нельзя отыграть — игра не принимает его как ставку.\n" +
                "• Долг не влияет на твой депозит напрямую, но если ты проиграешь с долгом — файл удалится снова.\n";

        Greeting = "Добро пожаловать!\n\n" +
                "Твой депозит: {deposit}\n" +
                "Твой долг: 0\n" +
                "Текущий уровень: 1\n" +
                "Доступные игры: кости, слоты\n\n" +
                "Если депозит упадёт до нуля — мы удалим твой файл.\n" +
                "     Ты вернёшься на 1-й уровень. С долгом.\n" +
                "     Долг отыграть нельзя. Он только растёт.\n\n" +
                "Введи \\help, чтобы узнать команды.\n" +
                "Введи \\play, чтобы начать.";

        RulesLevel1 = "ДОСТУПНЫЕ ИГРЫ: кости, слоты\n" +
                "Порог входа: 100 фишек\n" +
                "Порог перехода на ур. 2: 1000 фишек" +
                "Чтобы узнать правила игры в кости напиши \\bones\n" +
                "Чтобы узнать правила игры в слоты напили \\slots";

        RulesLevel2 = "ДОСТУПНЫЕ ИГРЫ: рулетка, русская рулетка\n" +
                "Порог входа: n+m фишек\n" +
                "Порог перехода на ур. 3: n+k фишек" +
                "Чтобы узнать правила игры в рулетку напиши \\roulette\n" +
                "Чтобы узнать правила игры в русскую рулетку напиши \\russianRoulette\n";

        RulesLevel3 = "ДОСТУПНЫЕ ИГРЫ: слоты, блэкджек, апгрейдер\n" +
                "Порог входа: n+k фишек\n" +
                "Порог перехода: МАКСИМУМ (финальный уровень)" +
                "Чтобы узнать правила игры в блекджэк напиши \\blackjack\n" +
                "Чтобы узнать правила игры в апгрейдер напиши \\upgrader\n";

        Bones = "правила игры в кости...";

        Slots = "\uD83C\uDFB0 СЛОТЫ\n" +
                "\n" +
                "Три барабана. Три символа.\n" +
                "Выигрыш — только когда все три совпали.\n" +
                "\n" +
                "Символы (от частого к редкому):\n" +
                "\uD83C\uDF52  →  ×2\n" +
                "\uD83C\uDF4B  →  ×3\n" +
                "BAR →  ×5\n" +
                "7 →  ×10\n" +
                "\n" +
                "Всё остальное — проигрыш. Ставка уходит в кассу.\n" +
                "\n" +
                "Пример:\n" +
                "\uD83C\uDF52 | \uD83C\uDF52 | \uD83C\uDF52  → ставка ×2\n" +
                "7 | 7 | 7  → ставка ×10\n" +
                "\uD83C\uDF52 | \uD83C\uDF52 | \uD83C\uDF4B  → проигрыш";

        Roulette = "правила игры в рулетку...";

        RussianRoulette = "правила игры в русскую рулетку...";

        Blackjack = "правила игры в блэкджек...";

        Upgrader = "правила игры в апгрейдер";

        Error = "ошибка! некорректный ввод";

        Defeat = "ДЕПОЗИТ = 0\n\n" +
                "Твой файл удалён, какой именно - ищи сам.\n" +
                "Ты возвращаешься на 1-й уровень.\n" +
                "Долг нельзя отыграть. Он только растёт.\n";

        Win = "ПОБЕДА.\n\n" +
                "Все 3 уровня пройдены.\n" +
                "Депозит: {deposit} — порог достигнут.\n\n" +
                "Ты обыграл казино.\n";
    }

    public String GetHelp(){ return Help; }
    public String GetGreeting(){ return Greeting; }
    public String GetRulesLevel1(){ return RulesLevel1; }
    public String GetRulesLevel2(){ return RulesLevel2; }
    public String GetRulesLevel3(){ return RulesLevel3; }
    public String GetRulesBones(){ return Bones; }
    public String GetSlots(){ return Slots; }
    public String GetRoulette(){ return Roulette; }
    public String GetRussianRoulette(){ return RussianRoulette; }
    public String GetBlackjack(){ return Blackjack; }
    public String GetUpgrader(){ return Upgrader; }
    public String GetError(){ return Error; }
    public String GetDefeat() {return Defeat; }
    public String GetWin(){return Win; }
}



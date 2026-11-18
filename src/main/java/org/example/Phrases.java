package org.example;

public class Phrases {
    private String Help;
    private String Greeting;
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
        Help = "ПРАВИЛА ИГРЫ\n\n" +
                "Управление происходит через кнопки под сообщениями. Текстовые команды отключены.\n\n" +
                "КАК ИГРАТЬ:\n" +
                "1. Нажми кнопку с названием игры.\n" +
                "2. Прочитай правила.\n" +
                "3. Напиши в чат сумму ставки (только целое число).\n\n" +
                "КНОПКИ:\n" +
                "• Кости / Слоты — выбрать игру\n" +
                "• Депозит — узнать текущий баланс\n" +
                "• Помощь — показать это сообщение\n" +
                "• Сброс — начать игру заново (депозит 100)\n\n" +
                "УРОВНИ:\n" +
                "Уровень 1: 500 очков\n" +
                "Уровень 2: 1000 очков\n" +
                "Уровень 3: 5000 очков\n\n";

        Greeting = "ДОБРО ПОЖАЛОВАТЬ!\n\n" +
                "Твой депозит: {deposit}\n" +
                "Текущий уровень: 1\n" +
                "Доступные игры: кости, слоты";


        RulesLevel2 = "ВЫ ПЕРЕШЛИ НА УРОВЕНЬ 2" +
                "ДОСТУПНЫЕ ИГРЫ: рулетка, русская рулетка\n" +
                "Порог входа: n+m фишек\n" +
                "Порог перехода на ур. 3: n+k фишек" +
                "Чтобы узнать правила игры в рулетку напиши \\roulette\n" +
                "Чтобы узнать правила игры в русскую рулетку напиши \\russianRoulette\n";

        RulesLevel3 = "ВЫ ПЕРЕШЛИ НА УРОВЕНЬ 3" +
                "ДОСТУПНЫЕ ИГРЫ: слоты, блэкджек, апгрейдер\n" +
                "Порог входа: n+k фишек\n" +
                "Порог перехода: МАКСИМУМ (финальный уровень)" +
                "Чтобы узнать правила игры в блекджэк напиши \\blackjack\n" +
                "Чтобы узнать правила игры в апгрейдер напиши \\upgrader\n";

        Bones = "🎲 КОСТИ\n\n" +
        "Бросаешь два кубика.\n\n" +
                "Правила:\n" +
                "• Сумма 7 или 11 → выигрыш ×3\n" +
                "• Сумма 2 или 12 → проигрыш (ставка уходит в кассу)\n" +
                "• Остальное → возврат ставки\n\n" +
                "Пример:\n" +
                "🎲 3 + 4 = 7 → ставка ×3\n" +
                "🎲 1 + 1 = 2 → проигрыш\n" +
                "🎲 2 + 3 = 5 → возврат ставки\n";

        Slots = "СЛОТЫ\n" +
                "\n" +
                "Три барабана. Три символа.\n" +
                "Выигрыш — когда все три символа совпали.\n" +
                "Возврат ставки — когда совпали два символа.\n" +
                "Проигрыш — когда все три символа разные.\n" +
                "\n" +
                "Символы (от частого к редкому):\n" +
                "\uD83C\uDF52  →  ×2\n" +
                "\uD83C\uDF4B  →  ×3\n" +
                "BAR →  ×5\n" +
                "7 →  ×10\n" +
                "\n" +
                "Примеры:\n" +
                "\uD83C\uDF52 | \uD83C\uDF52 | \uD83C\uDF52  → выигрыш ×2\n" +
                "7 | 7 | 7  → выигрыш ×10\n" +
                "\uD83C\uDF52 | \uD83C\uDF52 | \uD83C\uDF4B  → возврат ставки\n" +
                "BAR | 7 | \uD83C\uDF52  → проигрыш";

        Roulette = "правила игры в рулетку...";

        RussianRoulette = "правила игры в русскую рулетку...";

        Blackjack = "правила игры в блэкджек...";

        Upgrader = "правила игры в апгрейдер";

        Error = "ошибка! некорректный ввод";

        Defeat = "ДЕПОЗИТ = 0\n\n" +
                "Ты проиграл\n" +
                "Нажми на сброс чтобы начать игру сначала.";

        Win = "ПОБЕДА.\n\n" +
                "Все 3 уровня пройдены.\n" +
                "Депозит: {deposit} — порог достигнут.\n\n" +
                "Ты обыграл казино.\n";
    }

    public String GetHelp(){ return Help; }
    public String GetGreeting(){ return Greeting; }
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


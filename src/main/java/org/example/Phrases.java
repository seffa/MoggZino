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
    private String ApologyRequest;
    private String ApologyAccepted;
    private String ApologyNotNeeded;
    private String ResetLocked;

    public Phrases(){
        Help = "ПРАВИЛА ИГРЫ\n\n" +
                "Управление через кнопки под сообщениями.\n\n" +
                "КАК ИГРАТЬ:\n" +
                "1. Нажми кнопку с названием игры.\n" +
                "2. Если игра просит — выбери, на что ставишь.\n" +
                "3. Напиши в чат сумму ставки (только целое число).\n\n" +
                "КНОПКИ:\n" +
                "• Кнопки игр — выбрать игру\n" +
                "• Депозит — баланс и текущий зал\n" +
                "• Помощь — это сообщение\n" +
                "• Сброс — начать заново (депозит 100)\n\n" +
                "ЗАЛЫ:\n" +
                "Зал 1 — старт, депозит 100. Игры: кости, слоты.\n" +
                "Зал 2 — вход от 2000 фишек. Игры: рулетка, русская рулетка.\n" +
                "Зал 3 — вход от 5000 фишек. Игры: слоты, блэкджек, апгрейдер.\n\n" +
                "Зал не понижается, даже если депозит упал ниже порога.\n" +
                "Депозит 0 — игра окончена, нужен сброс.\n";

        Greeting = "ДОБРО ПОЖАЛОВАТЬ!\n\n" +
                "Твой депозит: {deposit}\n" +
                "Текущий зал: {level}\n" +
                "Доступные игры: {games}";


        RulesLevel2 = "🎉 ВЫ ПЕРЕШЛИ В ЗАЛ 2\n\n" +
                "Порог входа: 2000 фишек\n" +
                "Доступные игры: рулетка (русская рулетка — скоро)\n";

        RulesLevel3 = "🏆 ВЫ ПЕРЕШЛИ В ЗАЛ 3\n\n" +
                "Порог входа: 5000 фишек\n" +
                "Доступные игры: апгрейдер\n" +
                "Это финальный зал.\n";

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

        Roulette = "\uD83C\uDFB0 РУЛЕТКА\n" +
                "\n" +
                "Колесо выдаёт число от 0 до 36.\n" +
                "\n" +
                "Правила:\n" +
                "• Ставка Красное или Чёрное → выигрыш ×2 (возврат ставки + выигрыш 1:1)\n" +
                "• Ставка Зеро → выигрыш ×36 (возврат ставки + выигрыш 35:1)\n" +
                "• Если выпало Зеро, ставки на красное и чёрное проигрывают\n" +
                "• За раунд можно сделать только одну ставку\n" +
                "\n" +
                "Пример:\n" +
                "\uD83D\uDD34 Красное, выпало 17 → ставка ×2\n" +
                "⚫ Чёрное, выпало 0 → проигрыш\n" +
                "\uD83D\uDFE2 Зеро, выпало 0 → ставка ×36\n" +
                "\n";

        RussianRoulette = "🔫 РУССКАЯ РУЛЕТКА\n" +
                "\n" +
                "Барабан на 6 гнезд. Внутри случайно заряжено от 1 до 5 патронов.\n" +
                "\n" +
                "Правила:\n" +
                "• Ты делаешь один выстрел.\n" +
                "• Если патрон не выпал — выживаешь и получаешь выигрыш ×1.5.\n" +
                "• Если патрон выпал — проигрываешь ставку.\n" +
                "\n" +
                "Чем больше патронов в барабане, тем выше риск!\n";

        Blackjack = "🃏 БЛЭКДЖЕК\n\n" +
                "Цель игры — набрать очки, максимально близкие к 21, но не больше.\n\n" +
                "Правила:\n" +
                "• Карты 2-10 считаются по номиналу.\n" +
                "• Валет, Дама, Король — по 10 очков.\n" +
                "• Туз — 11 очков (или 1, если иначе будет перебор).\n" +
                "• Если у тебя сразу 21 (Блэкджек) — выплата 3:2.\n" +
                "• При ничьей ставка возвращается.\n\n" +
                "Ты получаешь карты, дилер добирает до 17 очков. Побеждает тот, у кого больше очков.";

        Upgrader = "⬆️ АПГРЕЙДЕР\n\n" +
                "Выбери множитель цели (x2, x5, x10).\n" +
                "Затем введи сумму ставки.\n\n" +
                "Если повезет — твой депозит мгновенно вырастет до цели.\n" +
                "Если нет — ставка сгорит.";


        Error = "ошибка! некорректный ввод";

        Defeat = "ДЕПОЗИТ = 0\n\n" +
                "Ты проиграл.\n\n" +
                "Чтобы начать заново:\n" +
                "1. Пришли кружок (видеосообщение) с извинениями за проигрыш.\n" +
                "2. Нажми кнопку Сброс.\n\n" +
                "Кружок увидит администратор бота.";

        ApologyRequest = "Сброс пока закрыт.\n\n" +
                "Пришли кружок с извинениями за проигрыш, потом нажми Сброс.\n" +
                "Кружок увидит администратор бота.";

        ApologyAccepted = "Извинения приняты ✅\n\n" +
                "Теперь нажми Сброс: депозит вернётся к 100, зал — к первому.";

        ApologyNotNeeded = "Кружок нужен только после проигрыша.\n" +
                "Сейчас у тебя есть фишки — играй.";

        ResetLocked = "Сброс доступен только при нулевом депозите.\n" +
                "Пока есть фишки — играй.";
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
    public String GetApologyRequest(){ return ApologyRequest; }
    public String GetApologyAccepted(){ return ApologyAccepted; }
    public String GetApologyNotNeeded(){ return ApologyNotNeeded; }
    public String GetResetLocked(){ return ResetLocked; }
}


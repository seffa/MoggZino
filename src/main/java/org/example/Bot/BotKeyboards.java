package org.example.Bot;

import org.example.hall.Level;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BotKeyboards {

    private static final Map<String, String> TITLES = Map.of(
            "bones", "🎲 Кости",
            "slots", "🎰 Слоты",
            "roulette", "🎡 Рулетка",
            "russian_roulette", "🔫 Русская рулетка",
            "blackjack", "🃏 Блэкджек",
            "upgrader", "⬆️ Апгрейдер");

    public static String title(String id) { return TITLES.getOrDefault(id, id); }

    private static InlineKeyboardButton button(String text, String data) {
        InlineKeyboardButton b = new InlineKeyboardButton();
        b.setText(text);
        b.setCallbackData(data);
        return b;
    }

    public static InlineKeyboardMarkup hall(Level hall) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> games = new ArrayList<>();
        for (String id : hall.ids()) {
            games.add(button(title(id), "menu_" + id));
        }
        rows.add(games);
        rows.add(List.of(button("Депозит", "menu_status"), button("Помощь", "menu_help")));
        rows.add(List.of(button("Сброс", "menu_reset")));

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
        return markup;
    }

    public static InlineKeyboardMarkup broke() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(List.of(
                List.of(button("Сброс", "menu_reset")),
                List.of(button("Помощь", "menu_help"))));
        return markup;
    }

    public static InlineKeyboardMarkup options(List<String> options) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String option : options) {
            row.add(button(option, "opt_" + option));
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(List.of(row));
        return markup;
    }
}
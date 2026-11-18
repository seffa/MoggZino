package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class BotKeyboards {

    public static InlineKeyboardMarkup getLevel1Keyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();


        List<InlineKeyboardButton> row1 = new ArrayList<>();

        InlineKeyboardButton bones = new InlineKeyboardButton();
        bones.setText("Кости");
        bones.setCallbackData("menu_bones");
        row1.add(bones);

        InlineKeyboardButton slots = new InlineKeyboardButton();
        slots.setText("Слоты");
        slots.setCallbackData("menu_slots");
        row1.add(slots);

        rows.add(row1);


        List<InlineKeyboardButton> row2 = new ArrayList<>();

        InlineKeyboardButton status = new InlineKeyboardButton();
        status.setText("Депозит");
        status.setCallbackData("menu_status");
        row2.add(status);

        InlineKeyboardButton help = new InlineKeyboardButton();
        help.setText("Помощь");
        help.setCallbackData("menu_help");
        row2.add(help);

        rows.add(row2);

        List<InlineKeyboardButton> row3 = new ArrayList<>();

        InlineKeyboardButton reset = new InlineKeyboardButton();
        reset.setText("Сброс");
        reset.setCallbackData("menu_reset");
        row3.add(reset);

        rows.add(row3);

        markup.setKeyboard(rows);
        return markup;
    }
}
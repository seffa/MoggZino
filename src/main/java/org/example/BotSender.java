package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotSender {

    private TelegramLongPollingBot bot;

    public BotSender(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    public void sendText(long cid, String text) {
        SendMessage sm = new SendMessage();
        sm.setChatId(String.valueOf(cid));
        sm.setText(text);
        try {
            bot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendLevel1Menu(long cid, String text) {
        SendMessage sm = new SendMessage();
        sm.setChatId(String.valueOf(cid));
        sm.setText(text);
        sm.setReplyMarkup(BotKeyboards.getLevel1Keyboard());
        try {
            bot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendCustomMenu(long cid, String text, InlineKeyboardMarkup markup) {
        SendMessage sm = new SendMessage();
        sm.setChatId(String.valueOf(cid));
        sm.setText(text);
        sm.setReplyMarkup(markup);
        try {
            bot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void answerCallback(String id) {
        AnswerCallbackQuery a = new AnswerCallbackQuery();
        a.setCallbackQueryId(id);
        try {
            bot.execute(a);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
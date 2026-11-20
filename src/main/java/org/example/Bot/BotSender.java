package org.example.Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotSender {

    private final TelegramLongPollingBot bot;

    public BotSender(TelegramLongPollingBot bot) { this.bot = bot; }

    public void sendText(long cid, String text) { send(cid, text, null); }

    public void sendCustomMenu(long cid, String text, InlineKeyboardMarkup markup) { send(cid, text, markup); }

    private void send(long cid, String text, InlineKeyboardMarkup markup) {
        SendMessage sm = new SendMessage();
        sm.setChatId(String.valueOf(cid));
        sm.setText(text);
        if (markup != null) sm.setReplyMarkup(markup);
        try {
            bot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public boolean forward(long toChat, long fromChat, int messageId) {
        ForwardMessage fm = new ForwardMessage();
        fm.setChatId(String.valueOf(toChat));
        fm.setFromChatId(String.valueOf(fromChat));
        fm.setMessageId(messageId);
        try {
            bot.execute(fm);
            return true;
        } catch (TelegramApiException e) {
            return false;
        }
    }

    public void sendVideoNote(long cid, String fileId) {
        SendVideoNote note = new SendVideoNote();
        note.setChatId(String.valueOf(cid));
        note.setVideoNote(new InputFile(fileId));
        try {
            bot.execute(note);
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
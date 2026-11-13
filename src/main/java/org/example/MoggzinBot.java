package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MoggzinBot extends TelegramLongPollingBot {

    private String uname;
    private Phrases ph = new Phrases();
    // карта чат -> игрок
    private Map<Long, Level1> users = new ConcurrentHashMap<>();

    public MoggzinBot(String token, String uname, DefaultBotOptions opt) {
        super(opt, token);
        this.uname = uname;
    }

    @Override
    public String getBotUsername() {
        return uname;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;
        Message msg = update.getMessage();
        long cid = msg.getChatId();
        String txt = msg.getText();
        sendAnswer(cid, handle(cid, txt));
    }

    private String handle(long cid, String txt) {
        Level1 lv = users.get(cid);
        if (lv == null) {
            lv = new Level1();
            users.put(cid, lv);
        }
        String n = norm(txt).toLowerCase();
        if (n.equals("/start")) {
            return ph.GetGreeting().replace("{deposit}", "" + lv.GetDeposit()) + "\n\n" + ph.GetHelp();
        }
        if (n.equals("/reset")) {
            users.put(cid, new Level1());
            return "сброс\nдепозит: 100";
        }
        String resp = lv.lv1_out(n);
        if (lv.GetDeposit() <= 0) {
            users.remove(cid);
            resp += "\n\n" + ph.GetDefeat();
        }
        return resp;
    }

    private String norm(String t) {
        t = t.trim();
        if (t.startsWith("\\")) return "/" + t.substring(1);
        return t;
    }

    private void sendAnswer(long cid, String t) {
        SendMessage sm = new SendMessage();
        sm.setChatId(cid + "");
        sm.setText(t);
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
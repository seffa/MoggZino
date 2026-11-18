package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MoggzinBot extends TelegramLongPollingBot {

    private String uname;
    private Phrases ph = new Phrases();
    private BotSender sender;

    private Map<Long, Level1> users = new ConcurrentHashMap<>();
    private Map<Long, String> waitingBet = new ConcurrentHashMap<>();

    public MoggzinBot(String token, String uname, DefaultBotOptions opt) {
        super(opt, token);
        this.uname = uname;
        this.sender = new BotSender(this);
    }

    @Override
    public String getBotUsername() {
        return uname;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
            return;
        }
        if (!update.hasMessage() || !update.getMessage().hasText()) return;
        Message msg = update.getMessage();
        handleText(msg.getChatId(), msg.getText());
    }

    private void handleCallback(CallbackQuery cq) {
        long cid = cq.getMessage().getChatId();
        String data = cq.getData();

        Level1 lv = users.get(cid);
        if (lv == null) {
            lv = new Level1();
            users.put(cid, lv);
        }

        sender.answerCallback(cq.getId());

        if (data.equals("menu_bones")) {
            waitingBet.put(cid, "bones");
            sender.sendText(cid, ph.GetRulesBones() + "\n\nВведи ставку целым числом:");

        } else if (data.equals("menu_slots")) {
            waitingBet.put(cid, "slots");
            sender.sendText(cid, ph.GetSlots() + "\n\nВведи ставку целым числом:");

        } else if (data.equals("menu_status")) {
            sender.sendText(cid, "Твой депозит: " + lv.GetDeposit());

        } else if (data.equals("menu_help")) {
            sender.sendText(cid, ph.GetHelp());

        } else if (data.equals("menu_reset")) {
            waitingBet.remove(cid);
            users.put(cid, new Level1());
            sender.sendLevel1Menu(cid, "Сброс. Депозит: 100");
        }
    }

    private void handleText(long cid, String txt) {
        Level1 lv = users.get(cid);
        if (lv == null) {
            lv = new Level1();
            users.put(cid, lv);
        }

        String n = txt.trim();

        if (n.equalsIgnoreCase("/start")) {
            waitingBet.remove(cid);
            String helloText = ph.GetGreeting().replace("{deposit}", String.valueOf(lv.GetDeposit()));
            sender.sendLevel1Menu(cid, helloText + "\n\n" + ph.GetHelp());
            return;
        }

        String game = waitingBet.get(cid);
        if (game != null) {
            int bet;
            try {
                bet = Integer.parseInt(n);
            } catch (NumberFormatException e) {
                sender.sendText(cid, ph.GetError());
                return;
            }

            if (!lv.IsValidBet(bet)) {
                sender.sendText(cid, ph.GetError());
                return;
            }

            waitingBet.remove(cid);
            String resp = lv.Game_level1(game, bet);

            if (lv.GetDeposit() <= 0) {
                users.remove(cid);
                resp += "\n\n" + ph.GetDefeat();
            }

            sender.sendLevel1Menu(cid, resp);
            return;
        }

        sender.sendLevel1Menu(cid, "Пользуйся кнопками");
    }
}
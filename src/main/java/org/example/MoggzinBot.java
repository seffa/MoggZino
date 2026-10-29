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

    private final String botUsername;
    private final Phrases phrases = new Phrases();

    private final Map<Long, Level1> users = new ConcurrentHashMap<>();

    public MoggzinBot(String botToken, String botUsername) {
        super(botToken);
        this.botUsername = botUsername;
    }

    public MoggzinBot(String botToken, String botUsername, DefaultBotOptions options) {
        super(options, botToken);
        this.botUsername = botUsername;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        Message message = update.getMessage();
        long chatId = message.getChatId();
        String text = message.getText();

        String answer = handleCommand(chatId, text);
        sendAnswer(chatId, answer);
    }

    private String handleCommand(long chatId, String text) {
        Level1 level = users.computeIfAbsent(chatId, id -> new Level1());

        String normalized = normalizeCommand(text);

        try {
            if (normalized.equalsIgnoreCase("/start")) {
                return phrases.GetGreeting()
                        .replace("{deposit}", String.valueOf(level.GetDeposit()))
                        + "\n\n"
                        + phrases.GetHelp();
            }

            if (normalized.equalsIgnoreCase("/reset")) {
                users.put(chatId, new Level1());
                return "Сессия сброшена.\nДепозит: 100";
            }

            String response = level.lv1_out(normalized);

            if (level.GetDeposit() <= 0) {
                users.remove(chatId);
                response += "\n\n" + phrases.GetDefeat();
            }

            return response;
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    private String normalizeCommand(String text) {
        if (text == null) {
            return "";
        }

        String trimmed = text.trim();

        if (trimmed.startsWith("\\")) {
            return "/" + trimmed.substring(1);
        }

        return trimmed;
    }

    private void sendAnswer(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
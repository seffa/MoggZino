package org.example;

import org.example.Bot.MoggzinBot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        // токен от botfather
        String token = System.getenv("TELEGRAM_BOT_TOKEN");
        String username = System.getenv("TELEGRAM_BOT_USERNAME");
        if (token == null || token.isBlank()) {
            System.out.println("не задан токен");
            return;
        }
        if (username == null || username.isBlank()) {
            System.out.println("не задан username бота");
            return;
        }

        DefaultBotOptions options = new DefaultBotOptions();
        String ph = System.getenv("TELEGRAM_PROXY_HOST");
        String pp = System.getenv("TELEGRAM_PROXY_PORT");
        if (ph != null && !ph.isBlank() && pp != null && !pp.isBlank()) {
            try {
                options.setProxyType(DefaultBotOptions.ProxyType.HTTP);
                options.setProxyHost(ph);
                options.setProxyPort(Integer.parseInt(pp));
                System.out.println("прокси " + ph + ":" + pp);
            } catch (NumberFormatException e) {
                System.out.println("кривой порт прокси");
                return;
            }
        }

        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new MoggzinBot(token, username, options));
            System.out.println("бот запущен");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
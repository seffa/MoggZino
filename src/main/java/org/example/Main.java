package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        String token = System.getenv("TELEGRAM_BOT_TOKEN");
        String username = System.getenv("TELEGRAM_BOT_USERNAME");

        if (token == null || token.isBlank()) {
            System.err.println("Не задан TELEGRAM_BOT_TOKEN.");
            return;
        }
        if (username == null || username.isBlank()) {
            System.err.println("Не задан TELEGRAM_BOT_USERNAME.");
            return;
        }

        DefaultBotOptions options = new DefaultBotOptions();

        // Прокси включается ТОЛЬКО если заданы обе переменные окружения.
        String proxyHost = System.getenv("TELEGRAM_PROXY_HOST");
        String proxyPort = System.getenv("TELEGRAM_PROXY_PORT");
        if (proxyHost != null && !proxyHost.isBlank()
                && proxyPort != null && !proxyPort.isBlank()) {
            try {
                // ВАЖНО: enum из самой библиотеки, НЕ java.net.Proxy.Type
                options.setProxyType(DefaultBotOptions.ProxyType.HTTP);
                options.setProxyHost(proxyHost);
                options.setProxyPort(Integer.parseInt(proxyPort));
                System.out.println("Использую HTTP-прокси: " + proxyHost + ":" + proxyPort);
            } catch (NumberFormatException e) {
                System.err.println("Неверный TELEGRAM_PROXY_PORT: " + proxyPort);
                return;
            }
        }

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MoggzinBot(token, username, options));
            System.out.println("Telegram bot запущен. Жду сообщения...");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
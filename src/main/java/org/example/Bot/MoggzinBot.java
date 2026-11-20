package org.example.Bot;

import org.example.Phrases;
import org.example.game.InteractiveGame;
import org.example.game.Blackjack;
import org.example.game.Game;
import org.example.game.QuickGame;
import org.example.player.Player;
import org.example.player.Players;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class MoggzinBot extends TelegramLongPollingBot {

    private final String uname;
    private final long adminId;
    private final Phrases ph = new Phrases();
    private final BotSender sender;
    private final Players players = new Players();

    public MoggzinBot(String token, String uname, DefaultBotOptions opt, long adminId) {
        super(opt, token);
        this.uname = uname;
        this.adminId = adminId;
        sender = new BotSender(this);
    }

    @Override
    public String getBotUsername() { return uname; }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
            return;
        }
        if (!update.hasMessage()) return;

        Message msg = update.getMessage();
        if (msg.hasVideoNote() || msg.hasVideo()) {
            handleApology(msg);
            return;
        }
        if (!msg.hasText()) return;
        handleText(msg);
    }

    private void handleCallback(CallbackQuery cq) {
        sender.answerCallback(cq.getId());

        String data = cq.getData();
        if (data == null || cq.getFrom() == null) return;
        if (!(cq.getMessage() instanceof Message msg) || !msg.isUserMessage()) return;

        long cid = msg.getChatId();
        Player player = players.get(cq.getFrom().getId());

        if (data.equals("bj_hit") || data.equals("bj_stand")) {
            InteractiveGame game = player.getActiveGame();
            if (game == null || game.isFinished()) {
                sender.sendCustomMenu(cid, "Игра уже окончена.", keyboard(player));
                return;
            }

            if (data.equals("bj_hit")) game.hit();
            else game.stand();

            if (game.isFinished()) {
                player.apply(game.getDelta());
                player.clearActiveGame();
                sender.sendCustomMenu(cid, game.state() + "\n\nтвой депозит: " + player.deposit(), keyboard(player));
            } else {
                sender.sendCustomMenu(cid, game.state(), BotKeyboards.blackjack());
            }
            return;
        }

        if (player.getActiveGame() != null) {
            sender.sendCustomMenu(cid, "Сначала заверши игру в Блэкджек! (Взять карту или Хватит)", BotKeyboards.blackjack());
            return;
        }

        if (data.startsWith("opt_")) {
            chooseOption(cid, player, data.substring(4));
            return;
        }
        if (!data.startsWith("menu_")) return;

        String id = data.substring(5);
        if (player.hall().has(id)) {
            offerGame(cid, player, id);
            return;
        }

        switch (id) {
            case "status" -> sender.sendCustomMenu(cid,
                    "Твой депозит: " + player.deposit() + "\nТекущий зал: " + player.level(),
                    keyboard(player));
            case "help" -> sender.sendCustomMenu(cid, ph.GetHelp(), keyboard(player));
            case "reset" -> resetPlayer(cid, player);
            default -> sender.sendText(cid, "Эта игра недоступна в твоем текущем зале. Нажми /start, чтобы обновить меню.");
        }
    }

    private void resetPlayer(long cid, Player player) {
        if (player.reset()) {
            sender.sendCustomMenu(cid, "Сброс. Депозит: " + player.deposit(), keyboard(player));
            return;
        }
        String text = player.broke() ? ph.GetApologyRequest() : ph.GetResetLocked();
        sender.sendCustomMenu(cid, text, keyboard(player));
    }

    private void handleApology(Message msg) {
        if (!msg.isUserMessage() || msg.getFrom() == null) return;

        long cid = msg.getChatId();
        Player player = players.get(msg.getFrom().getId());

        if (!player.apologize()) {
            sender.sendCustomMenu(cid, ph.GetApologyNotNeeded(), keyboard(player));
            return;
        }
        if (player.apologySent()) {
            deliver(msg, player);
            sender.sendCustomMenu(cid, ph.GetApologyAccepted(), keyboard(player));
        }
    }

    private void deliver(Message msg, Player player) {
        if (adminId == 0) return;

        String who = msg.getFrom().getUserName() == null ? "без username" : "@" + msg.getFrom().getUserName();
        sender.sendText(adminId, "Извинения за проигрыш\n" + who + "\nid: " + player.userId());

        if (!sender.forward(adminId, msg.getChatId(), msg.getMessageId())) {
            String fileId = msg.hasVideoNote() ? msg.getVideoNote().getFileId() : msg.getVideo().getFileId();
            sender.sendVideoNote(adminId, fileId);
        }
    }

    private void offerGame(long cid, Player player, String id) {
        if (player.broke()) {
            sender.sendCustomMenu(cid, ph.GetDefeat(), keyboard(player));
            return;
        }

        List<String> options = player.hall().options(id);
        if (!options.isEmpty()) {
            player.wait(id);
            sender.sendCustomMenu(cid, "На что ставишь?", BotKeyboards.options(options));
            return;
        }

        Game probe = player.hall().create(id, null, player.deposit());
        if (probe == null) {
            sender.sendText(cid, "Эта игра пока не готова");
            return;
        }

        player.wait(id);
        sender.sendText(cid, probe.rules() + "\n\nВведи ставку целым числом:");
    }

    private void chooseOption(long cid, Player player, String option) {
        String id = player.waiting();
        if (id == null || !player.hall().options(id).contains(option)) {
            sender.sendCustomMenu(cid, "Сначала выбери игру", keyboard(player));
            return;
        }
        Game probe = player.hall().create(id, option, player.deposit());
        if (probe == null) {
            sender.sendText(cid, ph.GetError());
            return;
        }
        player.wait(id, option);
        sender.sendText(cid, probe.rules() + "\n\nВведи ставку целым числом:");
    }

    private void handleText(Message msg) {
        if (!msg.isUserMessage() || msg.getFrom() == null) return;

        long cid = msg.getChatId();
        Player player = players.get(msg.getFrom().getId());
        String text = msg.getText().trim();

        if (text.equalsIgnoreCase("/start")) {
            if (player.getActiveGame() != null) {
                sender.sendCustomMenu(cid, "Сначала заверши игру в Блэкджек!", BotKeyboards.blackjack());
                return;
            }
            player.stopWaiting();
            String hello = ph.GetGreeting()
                    .replace("{deposit}", String.valueOf(player.deposit()))
                    .replace("{level}", String.valueOf(player.level()))
                    .replace("{games}", gamesList(player));
            if (player.broke()) hello += "\n\n" + ph.GetDefeat();
            sender.sendCustomMenu(cid, hello + "\n\n" + ph.GetHelp(), keyboard(player));
            return;
        }

        String id = player.waiting();
        if (id == null) {
            sender.sendCustomMenu(cid, "Пользуйся кнопками", keyboard(player));
            return;
        }
        if (player.broke()) {
            player.stopWaiting();
            sender.sendCustomMenu(cid, ph.GetDefeat(), keyboard(player));
            return;
        }

        int bet;
        try {
            bet = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            sender.sendText(cid, ph.GetError());
            return;
        }
        if (!player.canBet(bet)) {
            sender.sendText(cid, ph.GetError());
            return;
        }

        Game game = player.hall().create(id, player.option(), player.deposit());
        if (game == null) {
            player.stopWaiting();
            sender.sendText(cid, ph.GetError());
            return;
        }

        if (game instanceof QuickGame quickGame) {
            int before = player.level();
            player.apply(quickGame.play(bet));

            String answer = quickGame.result() + "\nтвой депозит: " + player.deposit();
            if (player.level() > before) answer += "\n\n" + levelUpText(player.level());
            if (player.broke()) answer += "\n\n" + ph.GetDefeat();

            sender.sendCustomMenu(cid, answer, keyboard(player));

        } else if (game instanceof Blackjack blackjack) {
            player.setActiveGame(blackjack);
            player.stopWaiting();
            blackjack.start(bet);

            if (blackjack.isFinished()) {
                player.apply(blackjack.getDelta());
                player.clearActiveGame();
                sender.sendCustomMenu(cid, blackjack.state() + "\n\nтвой депозит: " + player.deposit(), keyboard(player));
            } else {
                sender.sendCustomMenu(cid, blackjack.state(), BotKeyboards.blackjack());
            }
        }
    }

    private org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup keyboard(Player player) {
        return player.broke() ? BotKeyboards.broke() : BotKeyboards.hall(player.hall());
    }

    private String gamesList(Player player) {
        StringBuilder line = new StringBuilder();
        for (String id : player.hall().ids()) {
            if (line.length() > 0) line.append(", ");
            line.append(BotKeyboards.title(id));
        }
        return line.toString();
    }

    private String levelUpText(int level) {
        return switch (level) {
            case 2 -> ph.GetRulesLevel2();
            case 3 -> ph.GetRulesLevel3();
            default -> "";
        };
    }
}
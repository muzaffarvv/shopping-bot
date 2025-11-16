package uz.pdp;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.bot.RentoBot;


@Slf4j
public class MainBot {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new RentoBot());
            log.info("📍 Bot started successfully! ✅ ");
        } catch (TelegramApiException e) {
            log.info("Bot failed to start: {}⁉️", e.getMessage());
        }
    }
}

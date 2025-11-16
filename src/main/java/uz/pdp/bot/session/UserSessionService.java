package uz.pdp.bot.session;

import java.util.HashMap;
import java.util.Map;

public class UserSessionService {
    private final Map<Long, UserSession> sessions = new HashMap<>();

    public UserSession getSession(Long chatId) {
        return sessions.computeIfAbsent(chatId, id -> {
            UserSession session = new UserSession();
            session.reset();
            return session;
        });
    }
}


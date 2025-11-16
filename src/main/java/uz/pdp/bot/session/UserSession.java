package uz.pdp.bot.session;

import uz.pdp.bot.enums.BotState;

import java.util.ArrayDeque;
import java.util.Deque;

public class UserSession {
    private final Deque<BotState> stateStack = new ArrayDeque<>();

    public void pushState(BotState state) {
        stateStack.push(state);
    }

    public void popState() {
        if (stateStack.size() > 1) // Root menu holatida qolib ketmaslik uchun
            stateStack.pop();
    }

    public BotState getCurrentState() {
        return stateStack.peek();
    }

    public void reset() {
        stateStack.clear();
        stateStack.push(BotState.MAIN_MENU);
    }
}

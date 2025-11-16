package uz.pdp.bot.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.bot.factory.wrapper.RecordWrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class ReplyKeyboardMarkupFactory<T> {
    private final List<T> records;
    private final int colCount;

    public ReplyKeyboardMarkupFactory(List<T> records, int colCount) {
        this.records = records;
        this.colCount = colCount;
    }

    public ReplyKeyboardMarkup createReplyKeyboard() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        int index = 0;
        for (T record : records) {
            index++;

            RecordWrapper wrapper = wrapper(record);
            KeyboardButton button = new KeyboardButton(wrapper.getName());
            row.add(button);

            if (index % colCount == 0) {
                rows.add(row);
                row = new KeyboardRow();
            }
        }

        if (!row.isEmpty()) {
            rows.add(row);
        }
        KeyboardRow backRow = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("⬅️ Ortga qaytish");
        backRow.add(button);
        rows.add(backRow);

        markup.setKeyboard(rows);
        return markup;
    }

    protected abstract RecordWrapper wrapper(T t);
}


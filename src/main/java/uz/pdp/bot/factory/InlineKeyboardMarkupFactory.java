package uz.pdp.bot.factory;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.bot.factory.wrapper.RecordWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class InlineKeyboardMarkupFactory<T> {
    private final List<T> records;
    private final int colCount;
    private final int nextIndex;
    private final UUID categoryId;

    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        int totalSize = records.size();
        int endIndex = Math.min(nextIndex + 8, totalSize);

        int index = 0;
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = nextIndex; i < endIndex; i++) {
            T record = records.get(i);
            RecordWrapper wrapper = wrapper(record);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(wrapper.getName());
            button.setCallbackData(wrapper.getCommand() + wrapper.getId());
            row.add(button);
            index++;

            if (index % colCount == 0) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }

        if (!row.isEmpty()) {
            rows.add(row);
        }

        // todo: this buttons didn't entered if
        List<InlineKeyboardButton> navRow = getButtons(endIndex, totalSize);
        if (!navRow.isEmpty()) {
            rows.add(navRow);
        }

        inline.setKeyboard(rows);
        return inline;
    }

    private List<InlineKeyboardButton> getButtons(int endIndex, int totalSize) {
        List<InlineKeyboardButton> navRow = new ArrayList<>();
        if (nextIndex >= 8) {
            InlineKeyboardButton back = new InlineKeyboardButton("⬅️ Ortga");
            back.setCallbackData("page/" + categoryId +  "/" + (nextIndex - 8));
            navRow.add(back);
        }

        if (endIndex < totalSize) {
            InlineKeyboardButton next = new InlineKeyboardButton();
            next.setText("➡️ Keyingi");
            next.setCallbackData("page/" + categoryId + "/" + (nextIndex + 8));
            navRow.add(next);
        }
        return navRow;
    }

    protected abstract RecordWrapper wrapper(T t);
}


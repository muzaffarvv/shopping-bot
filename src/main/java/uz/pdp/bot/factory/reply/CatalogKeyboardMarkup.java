package uz.pdp.bot.factory.reply;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.bot.factory.ReplyKeyboardMarkupFactory;
import uz.pdp.bot.factory.wrapper.RecordWrapper;
import uz.pdp.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogKeyboardMarkup extends ReplyKeyboardMarkupFactory<Catalog> {
    public CatalogKeyboardMarkup(List<Catalog> records, int colCount) {
        super(records, colCount);
    }

    @Override
    protected RecordWrapper wrapper(Catalog catalog) {
        return RecordWrapper.builder()
                .id(catalog.getId())
                .name(catalog.getName())
                .command("CATALOG")
                .build();
    }

    public static ReplyKeyboardMarkup menuOrderKeyboard() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("✅ Tasdiqlash"));
        row1.add(new KeyboardButton("❌ Bekor qilish"));
        rows.add(row1);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("🖋️ Taxrirlash"));
        rows.add(row3);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("🔙 Ortga qaytish"));
        rows.add(row2);
        markup.setKeyboard(rows);
        return markup;
    }

    public static ReplyKeyboardMarkup viewCartMenuKeyboard() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("🛒 Savat"));
        rows.add(row1);

        KeyboardRow row2 = new KeyboardRow(); // todo: Back to Main Menu
        row2.add(new KeyboardButton("⏪ Ortga qaytish"));
        rows.add(row2);

        markup.setKeyboard(rows);
        return  markup;
    }

    public static ReplyKeyboardMarkup menuKeyboard() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("🗂️ Kategoriyalar ro'yxati"));
        rows.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("🛒 Savat"));
        rows.add(row2);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("📦 Mening buyurtmalarim"));
        row3.add(new KeyboardButton("📞 Aloqa"));
        rows.add(row3);

        KeyboardRow row4 = new KeyboardRow();
        row4.add(new KeyboardButton("ℹ️ Biz haqimizda"));
        rows.add(row4);

        markup.setKeyboard(rows);
        return markup;

    }
}

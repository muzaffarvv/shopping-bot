package uz.pdp.bot.service;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.bot.factory.inline.ProductInlineKeyboardMarkup;
import uz.pdp.bot.factory.wrapper.RecordWrapper;
import uz.pdp.bot.memory.ProductMemoryService;
import uz.pdp.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductBotService {
    private final ProductMemoryService productMemoryService;


    public InlineKeyboardMarkup getInlineKeyboard(UUID categoryId) {
        List<Product> allProducts = productMemoryService.getProductsByCategoryId(categoryId);

        return new ProductInlineKeyboardMarkup(
                allProducts,
                2, // colCount
                0, // nextIndex  //
                categoryId
        ).createInlineKeyboard();
    }

    public InlineKeyboardMarkup createEditProductButton(int count, UUID productId) {
        InlineKeyboardMarkup inline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> navRow = new ArrayList<>();

        List<InlineKeyboardButton> row1 = getInlineKeyboardButtons(count, productId);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton addCart = new InlineKeyboardButton("📥 Savatga qo'shish");
        addCart.setCallbackData("cart/addCart/" + productId );
        row2.add(addCart);

        navRow.add(row1);
        navRow.add(row2);

        inline.setKeyboard(navRow);
        return inline;
    }

    private static List<InlineKeyboardButton> getInlineKeyboardButtons(int count, UUID productId) {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton back = new InlineKeyboardButton("-");
        back.setCallbackData("null/minus/" + productId);
        row1.add(back);

        InlineKeyboardButton numberButton = new InlineKeyboardButton(String.valueOf(count));
        numberButton.setCallbackData("number");
        row1.add(numberButton);

        InlineKeyboardButton next = new InlineKeyboardButton("+");
        next.setCallbackData("null/plus/" + productId);
        row1.add(next);
        return row1;
    }

    public InlineKeyboardMarkup createEditProductButton(List<Product> products) {
        InlineKeyboardMarkup inline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> navRow = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        int index = 0;

        for (Product product : products) {
            index++;
            InlineKeyboardButton button = new InlineKeyboardButton(product.getName());
            button.setCallbackData("Product" + product.getId());
            row.add(button);

            if (index % 2 == 0) {
                navRow.add(row);
                row = new ArrayList<>();
            }
        }

        if (!row.isEmpty()) {
            navRow.add(row);
        }

        inline.setKeyboard(navRow);
        return inline;
    }

    private static RecordWrapper recordWrapper(Product product) {
        return RecordWrapper.builder()
                .id(product.getId())
                .name(product.getName())
                .command("Product/")
                .build();
    }


}

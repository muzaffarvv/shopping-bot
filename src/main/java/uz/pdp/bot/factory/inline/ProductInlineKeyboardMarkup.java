package uz.pdp.bot.factory.inline;

import uz.pdp.bot.factory.InlineKeyboardMarkupFactory;
import uz.pdp.bot.factory.wrapper.RecordWrapper;
import uz.pdp.model.Product;

import java.util.List;
import java.util.UUID;

public class ProductInlineKeyboardMarkup extends InlineKeyboardMarkupFactory<Product> {

    public ProductInlineKeyboardMarkup(
                                       List<Product> allProducts,
                                       int colCount,
                                       int nextIndex,
                                       UUID categoryId) {
        super(allProducts, colCount, nextIndex, categoryId);
    }

    @Override
    protected RecordWrapper wrapper(Product product) {
        return RecordWrapper.builder()
                .id(product.getId())
                .name(product.getName())
                .command("Product")
                .build();
    }
}
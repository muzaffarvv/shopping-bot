package uz.pdp.bot.service;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.pdp.bot.factory.reply.CatalogKeyboardMarkup;
import uz.pdp.bot.memory.CatalogMemoryService;
import uz.pdp.model.Catalog;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CatalogBotService {

    private final CatalogMemoryService memoryService;

    public ReplyKeyboardMarkup getChildKeyboardByName(String parentName) {
        Catalog category = memoryService.getByName(parentName);
        List<Catalog> catalogs = memoryService.getChildren(category.getId());

        List<Catalog> extended = new ArrayList<>(catalogs);
        return new CatalogKeyboardMarkup(extended, 2).createReplyKeyboard();
    }

    public Catalog getCatalogByName(String name) {
        return memoryService.getByName(name);
    }

    public ReplyKeyboardMarkup getRootCategories() {
        List<Catalog> catalogs = memoryService.getRootCategories();
        return new CatalogKeyboardMarkup(catalogs,2).createReplyKeyboard();
    }
}
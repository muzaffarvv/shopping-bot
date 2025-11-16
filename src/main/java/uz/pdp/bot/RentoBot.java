package uz.pdp.bot;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.bot.enums.BotState;
import uz.pdp.bot.factory.inline.ProductInlineKeyboardMarkup;
import uz.pdp.bot.factory.reply.CatalogKeyboardMarkup;
import uz.pdp.bot.memory.ProductMemoryService;
import uz.pdp.bot.service.CatalogBotService;
import uz.pdp.bot.memory.CatalogMemoryService;
import uz.pdp.bot.service.ProductBotService;
import uz.pdp.bot.session.UserSession;
import uz.pdp.bot.session.UserSessionService;
import uz.pdp.model.Product;
import uz.pdp.renderer.ProductRenderer;
import uz.pdp.service.CatalogService;
import uz.pdp.service.ProductService;

import java.util.*;

@RequiredArgsConstructor
public class RentoBot extends TelegramLongPollingBot {

    private static final String USER_NAME = "t.me/Rento_helper_bot";
    private static final String BOT_TOKEN = "8037787062:AAFZdhkYO_07wC1sqygnaHcBJZs6eAFW2bE";

    private final UserSessionService userSessionService = new UserSessionService();

    private final CatalogService catalogService = new CatalogService();
    private final CatalogMemoryService memoryService = new CatalogMemoryService(catalogService.getAll());
    private final CatalogBotService catalogBotService = new CatalogBotService(memoryService);

    private final ProductService productService = new ProductService();
    private final ProductMemoryService productMemoryService = new ProductMemoryService(productService.getAll());
    private final ProductBotService productBotService = new ProductBotService(productMemoryService);

    static Map<Long, Map<UUID, Integer>> stateForEditProduct = new HashMap<>();
    static Map<Long, List<Product>> cartProducts = new HashMap<>();

    static Map<Long, Integer> userGetProductIndexes = new HashMap<>();
    static Map<Long, Map<UUID, Product>> myOrders = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            SendMessage sendMessage = new SendMessage();
            message.setChatId(chatId);
            sendMessage.setChatId(chatId);

            String text = update.getMessage().getText();

            UserSession session = userSessionService.getSession(chatId);
            List<Product> cart = cartProducts.get(chatId);
            switch (text) {
                case "/start" -> { // todo change some commands
                    session.reset();
                    message.setText("📋 Asosiy Menyu");
                    message.setReplyMarkup(CatalogKeyboardMarkup.menuKeyboard());
                }
                case "🗂️ Kategoriyalar ro'yxati" -> {
                    session.pushState(BotState.MAIN_MENU);
                    message.setText("Quyidagi kategoriyalardan birini tanlang:");
                    message.setReplyMarkup(catalogBotService.getRootCategories());
                }
                // todo: count always one
                case "🛒 Savat" -> {
                    session.pushState(BotState.VIEW_CART);

                    if (cart == null || cart.isEmpty()) {
                        message.setText("🛒 Savatingiz bo‘m-bo'sh ekan ☹️");
                        message.setReplyMarkup(CatalogKeyboardMarkup.menuKeyboard());
                    } else {
                        StringBuilder sb = new StringBuilder();
                        Map<UUID, Integer> productCounts = stateForEditProduct.get(chatId);

                        Integer count;
                        double totalPrice = 0;
                        for (Product product : cart) {
                            Map<UUID, Integer> productsMap = stateForEditProduct.get(chatId);
                            count = productsMap.get(product.getId());
                            totalPrice += product.getPrice() * count;
                            sb.append(ProductRenderer.renderForCart(product, count)).append("\n");
                        }
                        message.setText("""
                                🛒 Savatingizda quyidagilar bor:
                                
                                %s
                                
                                💰 Total price: %,.0f %s
                                ⁉️ Buyurtmani tasdiqlaysizmi?
                                """.formatted(sb.toString().trim(), totalPrice, "USD"));
                    }
                    message.setReplyMarkup(CatalogKeyboardMarkup.menuOrderKeyboard());
                }
                case "🔙 Ortga qaytish" -> { // todo: for edit cart
                    message.setText("Asosiy Menyu");
                    message.setReplyMarkup(CatalogKeyboardMarkup.menuKeyboard());
                }
                case "⏪ Ortga qaytish" -> {
                    message.setText("Asosiy Menyu");
                    message.setReplyMarkup(CatalogKeyboardMarkup.menuKeyboard());
                }
                case "❌ Bekor qilish" -> {
                    message.setText("⛔ Buyurtma bekor qilindi ");
                    cartProducts.remove(chatId);
                    message.setReplyMarkup(CatalogKeyboardMarkup.menuKeyboard());
                }
                case "✅ Tasdiqlash" -> { // todo: product in user's cart with descriptions
                    message.setText("👤 Hurmatli " + update.getMessage().getFrom().getFirstName() +
                            "\n\n📝 Buyurtma tasdiqlandi.\n\n" +
                            "⌛ Buyurtma tafsilotlari bo‘yicha tez orada bog‘lanamiz.\n");
                    message.setReplyMarkup(CatalogKeyboardMarkup.menuKeyboard());
                    cartProducts.remove(chatId);

                }
                case "🖋️ Taxrirlash" -> {
                    if (cart == null || cart.isEmpty()) {
                        message.setText("🛒 Savatingiz bo‘m-bo'sh ekan ☹️");
                        message.setReplyMarkup(CatalogKeyboardMarkup.menuOrderKeyboard());
                    } else {
                        StringBuilder sb = new StringBuilder();
                        Map<UUID, Integer> productCounts = stateForEditProduct.get(chatId);

                        Integer count;
                        double totalPrice = 0;
                        for (Product product : cart) {
                            Map<UUID, Integer> productsMap = stateForEditProduct.get(chatId);
                            count = productsMap.get(product.getId());
                            totalPrice += product.getPrice() * count;
                            sb.append(ProductRenderer.renderForCart(product, count)).append("\n");
                        }
                        message.setText("""
                                🛒 Savatingizda quyidagilar bor:
                                
                                %s
                                
                                💰 Total price: %,.0f %s
                                ⁉️ Buyurtmani tasdiqlaysizmi?
                                """.formatted(sb.toString().trim(), totalPrice, "USD"));
                        if (!cart.isEmpty()) {
                            message.setReplyMarkup(productBotService.createEditProductButton(cart));
                        }
                    }
                }
                case "📦 Mening buyurtmalarim" -> { // todo: history orders
                    message.setText("👤 Hurmatli " + update.getMessage().getFrom().getFirstName() +
                            "\n\n ⚙️ Ushbu bo‘lim ustida ishlanmoqda, Tez orada buyurtmalaringizni ko‘ra olasiz. \n" );
                }
                case "📞 Aloqa" -> {
                    message.setText("""
                            <b>📞 Kontaktlar | Call center</b>\s
                            
                            Toshkent    <code>+998 23 901 01 01</code>
                            Andijon     <code>+998 23 713 13 13</code>
                            Nukus       <code>+998 61 704 04 04</code>
                            Samarqand   <code>+998 23 517 17 17</code>""");
                    message.setParseMode("HTML");
                }
                case "ℹ️ Biz haqimizda" -> {
                    message.setText("""
                            📍 Rento — bu 2025-yil iyul oyida ishga tushirilgan, sotuv, xarid va ijara jarayonlarini soddalashtirish uchun yaratilgan Telegram boti.
                            🎯 Botning asosiy maqsadi — foydalanuvchilarga turli mahsulot va xizmatlarni tez, qulay va ishonchli tarzda almashish imkonini yaratishdir.
                            🔧 Rento doimiy rivojlanishda bo‘lib, oddiy interfeys, tezkor ishlash va foydalanuvchiga qulay navigatsiya bilan ajralib turadi.
                            ✅ Rento — zamonaviy ehtiyojlar uchun sodda va ishonchli yechim.
                            
                            🔗 Rento 🦦  bot havolasi: https://t.me/Rento_helper_bot ✌""");
                }
                case "🚗 Mashinalar" -> {
                    session.pushState(BotState.CAR_MENU);
                    message.setText("Qanday turdagi mashina hohlaysiz:");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🚗 Mashinalar"));
                }
                case "🏠 Uylar" -> {
                    session.pushState(BotState.HOUSE_MENU);
                    message.setText("Quyidagi kategoriyalardan birini tanlang:");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🏠 Uylar"));
                }
                case "🛠️ Texnikalar" -> {
                    session.pushState(BotState.TECH_MENU);
                    message.setText("Quyidagi kategoriyalardan birini tanlang:");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🛠️ Texnikalar"));
                }
                case "📱 Telefonlar" -> {
                    session.pushState(BotState.PHONE_MENU);
                    message.setText("Quyidagi kategoriyalardan birini tanlang:");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("📱 Telefonlar"));
                }
                case "🚛 Yuk mashinalar" -> {
                    session.pushState(BotState.TRUCK_MENU);
                    message.setText("Yuk mashina brendini tanlashingiz mumkin 👇");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🚛 Yuk mashinalar"));
                }
                case "🚗 Yengil mashinalar" -> {
                    session.pushState(BotState.LIGHT_CAR_MENU);
                    message.setText("Yengil mashina brendini tanlashingiz mumkin 👇");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🚗 Yengil mashinalar"));
                }
                case "Ijaraga beriladigan" -> {
                    session.pushState(BotState.FOR_RENT);
                    message.setText("Ijaraga beriladigan Obyektlar:");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("Ijaraga beriladigan"));
                }
                case "Sotiladigan" -> {
                    session.pushState(BotState.FOR_SALE);
                    message.setText("Sotiladigan Obyektlar:");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("Sotiladigan"));
                }
                case "📱 Smartfonlar" -> {
                    session.pushState(BotState.SMART_PHONE);
                    message.setText("Smartphonelar brendini tanlashizgiz mumkin");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("📱 Smartfonlar"));
                }
                case "☎ Tugmali telefonlar" -> {
                    session.pushState(BotState.FLIPPY_PHONE);
                    message.setText("Telefon brendini tanlashizgiz mumkin");
                    message.setReplyMarkup(catalogBotService.getChildKeyboardByName("☎ Tugmali telefonlar"));
                }
                case "MAN" -> {
                    UUID manId = memoryService.getByName("MAN").getId();
//                    //todo: bizda user bor va u birinchi bosganda unga productGetIndex 0 ni set qilamiz
//                    userGetProductIndexes.put(chatId, 0);
                    message.setText("🔥 Siz uchun topilgan MAN modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(manId));
                }
                case "Office texnikasi" -> {
                    UUID officeId = memoryService.getByName("Office texnikasi").getId();
                    message.setText("Office texnikalari:");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(officeId));
                }
                case "Oshxona texnikasi" -> {
                    UUID kitchenId = memoryService.getByName("Oshxona texnikasi").getId();
                    message.setText("Oshxona texnikalari");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(kitchenId));
                }
                case "Isuzu" -> {
                    UUID id = memoryService.getByName("Isuzu").getId();
                    message.setText("🔥 Siz uchun topilgan Isuzu modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Volvo Trucks" -> {
                    UUID id = memoryService.getByName("Volvo Trucks").getId();
                    message.setText("🔥 Siz uchun topilgan Volvo Trucks modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Chevrolet" -> {
                    UUID id = memoryService.getByName("Chevrolet").getId();
                    message.setText("🔥 Siz uchun topilgan Chevrolet modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Audi" -> {
                    UUID id = memoryService.getByName("Audi").getId();
                    message.setText("🔥 Siz uchun topilgan Audi modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Volkswagen (VW)" -> {
                    UUID id = memoryService.getByName("Volkswagen (VW)").getId();
                    message.setText("🔥 Siz uchun topilgan Volkswagen modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Toyota" -> {
                    UUID id = memoryService.getByName("Toyota").getId();
                    message.setText("🔥 Siz uchun topilgan Toyota modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Kvartira (ijara)" -> {
                    UUID id = memoryService.getByName("Kvartira (ijara)").getId();
                    message.setText("🏠 Ijaraga berilayotgan kvartiralar 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Hovli uy (ijara)" -> {
                    UUID id = memoryService.getByName("Hovli uy (ijara)").getId();
                    message.setText("🏡 Ijaraga berilayotgan hovli uylari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Studio (ijara)" -> {
                    UUID id = memoryService.getByName("Studio (ijara)").getId();
                    message.setText("🎬 Ijaraga berilayotgan studiya turidagi uylar 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Kvartira (sotuv)" -> {
                    UUID id = memoryService.getByName("Kvartira (sotuv)").getId();
                    message.setText("🏢 Sotuvdagi kvartiralar 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Hovli uy (sotuv)" -> {
                    UUID id = memoryService.getByName("Hovli uy (sotuv)").getId();
                    message.setText("🏡 Sotuvdagi hovli uylari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Villa (sotuv)" -> {
                    UUID id = memoryService.getByName("Villa (sotuv)").getId();
                    message.setText("🏘️ Sotuvdagi villalar 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "iPhone" -> {
                    UUID id = memoryService.getByName("iPhone").getId();
                    message.setText("📱 iPhone modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Samsung" -> {
                    UUID id = memoryService.getByName("Samsung").getId();
                    message.setText("📱 Samsung modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Xiaomi" -> {
                    UUID id = memoryService.getByName("Xiaomi").getId();
                    message.setText("📱 Xiaomi modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Realme" -> {
                    UUID id = memoryService.getByName("Realme").getId();
                    message.setText("📱 Realme modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Infinix" -> {
                    UUID id = memoryService.getByName("Infinix").getId();
                    message.setText("📱 Infinix modellari 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Sony Ericsson" -> {
                    UUID id = memoryService.getByName("Sony Ericsson").getId();
                    message.setText("📠 Sony Ericsson tugmali telefonlar 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Nokia" -> {
                    UUID id = memoryService.getByName("Nokia").getId();
                    message.setText("📠 Nokia tugmali telefonlar 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "Samsung flippy" -> {
                    UUID id = memoryService.getByName("Samsung flippy").getId();
                    message.setText("📠 Flip uslubidagi Samsung tugmali telefonlar 👇");
                    message.setReplyMarkup(productBotService.getInlineKeyboard(id));
                }
                case "⬅️ Ortga qaytish" -> {
                    if (session.getCurrentState() == BotState.MAIN_MENU) {
                        message.setText("Asosiy Menyu");
                        message.setReplyMarkup(CatalogKeyboardMarkup.menuKeyboard());
                    } else if (session.getCurrentState() == BotState.CAR_MENU || session.getCurrentState() == BotState.HOUSE_MENU ||
                            session.getCurrentState() == BotState.TECH_MENU || session.getCurrentState() == BotState.PHONE_MENU) {
                        session.popState();
                        message.setText("Quyidagi kategoriyalardan birini tanlang:");
                        message.setReplyMarkup(catalogBotService.getRootCategories());
                    } else if (session.getCurrentState() == BotState.TRUCK_MENU || session.getCurrentState() == BotState.LIGHT_CAR_MENU) {
                        session.popState();
                        message.setText("Qanday turdagi Mashina hohlaysiz:");
                        message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🚗 Mashinalar"));
                    } else if (session.getCurrentState() == BotState.FOR_SALE || session.getCurrentState() == BotState.FOR_RENT) {
                        session.popState();
                        message.setText("Qanday turdagi Obyekt hohlaysiz:");
                        message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🏠 Uylar"));
                    } else if (session.getCurrentState() == BotState.TECH_KITCHEN || session.getCurrentState() == BotState.TECH_OFFICE) {
                        session.popState();
                        message.setText("Qanday turdagi Texnika hohlaysiz:");
                        message.setReplyMarkup(catalogBotService.getChildKeyboardByName("🛠️ Texnikalar"));
                    } else if (session.getCurrentState() == BotState.FLIPPY_PHONE || session.getCurrentState() == BotState.SMART_PHONE) {
                        session.popState();
                        message.setText("Qanday turdagi Smartphone hohlaysiz:");
                        message.setReplyMarkup(catalogBotService.getChildKeyboardByName("📱 Telefonlar"));
                    }
                }
            }
            try {
                execute(message);
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long chatId = callbackQuery.getMessage().getChatId();
            String data = callbackQuery.getData();
            if (data.startsWith("Product")) {
                UUID productId = UUID.fromString(data.substring(7));
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                Product product = productMemoryService.getProductById(productId);
                sendMessage.setText(ProductRenderer.render(productMemoryService.getProductById(productId), product.getPrice()));
                sendMessage.setReplyMarkup(productBotService.createEditProductButton(1, productId));

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (data.startsWith("null")) {  // else if () {
                String[] split = data.split("/");
                if (split.length == 3) {
                    String command = split[1];
                    UUID productId = UUID.fromString(split[2]);
                    Product prod = productMemoryService.getProductById(productId);
                    stateForEditProduct.putIfAbsent(chatId, new HashMap<>());
                    Map<UUID, Integer> map = stateForEditProduct.get(chatId);
                    map.putIfAbsent(productId, 1);

                    int count = map.get(productId);
                    if (command.equals("plus") && count < prod.getQuantity()) {
                        count++;
                    } else if (command.equals("minus")) {
                        if (count > 1) count--;
                    }
                    map.put(productId, count);
                    EditMessageText sendMessage = new EditMessageText();

                    double totalPrice = count * prod.getPrice();

                    sendMessage.setText(ProductRenderer.render(productMemoryService.getProductById(productId), totalPrice));
                    sendMessage.setChatId(chatId);
                    sendMessage.setMessageId(callbackQuery.getMessage().getMessageId());
                    sendMessage.setReplyMarkup(productBotService.createEditProductButton(count, productId));
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (data.startsWith("cart/")) {
                String[] splitCart = data.split("/");
                if (splitCart.length == 3) {
                    UUID productId = UUID.fromString(splitCart[2]);
                    Map<UUID, Integer> productsMap = stateForEditProduct.get(chatId);
                    Integer i = productsMap.get(productId);
                    productsMap.put(productId, 1);

                    List<Product> products = new ArrayList<>();
                    products.add(productMemoryService.getProductById(productId));
                    cartProducts.putIfAbsent(chatId, products);
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("📥 Savatga qo'shildi");
                    CatalogKeyboardMarkup.viewCartMenuKeyboard();
                    sendMessage.setReplyMarkup(CatalogKeyboardMarkup.viewCartMenuKeyboard());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else if (data.startsWith("page/")) {
                // Navigatsiya uchun yangi handler
                String[] parts = data.split("/");
                UUID categoryId = UUID.fromString(parts[1]);
                int nextIndex = Integer.parseInt(parts[2]);

                List<Product> allProducts = productMemoryService.getProductsByCategoryId(categoryId);

                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId(callbackQuery.getMessage().getMessageId());
                message.setText("Mahsulotlar ro'yxati:");
                message.setReplyMarkup(new ProductInlineKeyboardMarkup(
                        allProducts,
                        2, // colCount
                        nextIndex,
                        categoryId
                ).createInlineKeyboard());

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return USER_NAME;
    }

    /**
     * @return bot token
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onRegister() {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/start", " botni ishga tushurish"));
        // boshqa komandalarni ham qo‘shish mumkin
        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}


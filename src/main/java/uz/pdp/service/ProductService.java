package uz.pdp.service;

import uz.pdp.base.BaseService;
import uz.pdp.bot.memory.CatalogMemoryService;
import uz.pdp.model.Product;
import uz.pdp.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductService implements BaseService<Product> {
    private static final String FILE_NAME = "products.json";
    private static final List<Product> products = new ArrayList<>();
    public ProductService() {
    }

    static {
        CatalogService catalogService = new CatalogService();
        CatalogMemoryService catalogMemoryService = new CatalogMemoryService(catalogService.getAll());

        UUID manId = catalogMemoryService.getByName("MAN").getId();
        UUID isuzuId = catalogMemoryService.getByName("Isuzu").getId();
        UUID volvoId = catalogMemoryService.getByName("Volvo Trucks").getId();

        UUID chevroletId = catalogMemoryService.getByName("Chevrolet").getId();
        UUID audiId = catalogMemoryService.getByName("Audi").getId();
        UUID volkswagenId = catalogMemoryService.getByName("Volkswagen (VW)").getId();
        UUID toyotaId = catalogMemoryService.getByName("Toyota").getId();

        UUID rentKvId = catalogMemoryService.getByName("Kvartira (ijara)").getId();
        UUID rentHovliId = catalogMemoryService.getByName("Hovli uy (ijara)").getId();
        UUID rentStudioId = catalogMemoryService.getByName("Studio (ijara)").getId();

        UUID saleKvId = catalogMemoryService.getByName("Kvartira (sotuv)").getId();
        UUID saleHovliId = catalogMemoryService.getByName("Hovli uy (sotuv)").getId();
        UUID saleVillaId = catalogMemoryService.getByName("Villa (sotuv)").getId();


        UUID iphoneId = catalogMemoryService.getByName("iPhone").getId();
        UUID samsungId = catalogMemoryService.getByName("Samsung").getId();
        UUID xiaomiId = catalogMemoryService.getByName("Xiaomi").getId();
        UUID realmeId = catalogMemoryService.getByName("Realme").getId();
        UUID infinixId = catalogMemoryService.getByName("Infinix").getId();

        UUID sonyEricssonId = catalogMemoryService.getByName("Sony Ericsson").getId();
        UUID nokiaId = catalogMemoryService.getByName("Nokia").getId();
        UUID samsungFlippyId = catalogMemoryService.getByName("Samsung flippy").getId();

        UUID officeId = catalogMemoryService.getByName("Office texnikasi").getId();
        UUID kitchenId = catalogMemoryService.getByName("Oshxona texnikasi").getId();

        // Office texnikasi Products (12 items)
        products.add(new Product("HP LaserJet Pro", "Yangi, lazer printer; yuqori sifatli chop etish", 300, 3, 2023, officeId));
        products.add(new Product("Epson Scanner", "Ishlatilgan, skaner; tezkor va aniq skanerlash", 150, 2, 2020, officeId));
        products.add(new Product("Dell Monitor 27", "Yangi, 4K monitor; keng ekran, yuqori aniqlik", 400, 4, 2023, officeId));
        products.add(new Product("Canon Pixma", "Yangi, rangli printer; uy va ofis uchun ideal", 200, 3, 2023, officeId));
        products.add(new Product("Logitech Webcam", "Yangi, HD veb-kamera; videokonferensiyalar uchun", 100, 2, 2023, officeId));
        products.add(new Product("Brother MFC", "Ishlatilgan, ko‘p funksiyali printer; chop etish va skaner", 250, 3, 2020, officeId));
        products.add(new Product("Acer Projector", "Yangi, Full HD proyektor; taqdimotlar uchun ideal", 500, 4, 2023, officeId));
        products.add(new Product("HP Desktop PC", "Ishlatilgan, ofis kompyuteri; ishonchli va tezkor", 350, 3, 2020, officeId));
        products.add(new Product("Epson EcoTank", "Yangi, siyohli printer; tejamkor va sifatli", 250, 3, 2023, officeId));
        products.add(new Product("Asus Router", "Yangi, Wi-Fi router; yuqori tezlik, keng qamrov", 150, 2, 2023, officeId));
        products.add(new Product("Lenovo Laptop", "Yangi, biznes noutbuki; tezkor va engil", 600, 4, 2023, officeId));
        products.add(new Product("Xerox Multifunction", "Ishlatilgan, ko‘p funksiyali printer; ishonchli", 300, 3, 2020, officeId));

        // Oshxona texnikasi Products (13 items)
        products.add(new Product("Samsung Muzlatgich", "Yangi, ikki eshikli; keng va energiya tejovchi", 600, 4, 2023, kitchenId));
        products.add(new Product("LG Mikroto‘lqinli pech", "Ishlatilgan, 30 litr; gril va tezkor isitish", 150, 3, 2020, kitchenId));
        products.add(new Product("Bosch Gaz plitasi", "Yangi, 4 zonali; zamonaviy dizayn, xavfsiz", 400, 4, 2023, kitchenId));
        products.add(new Product("Philips Blender", "Yangi, 600W; smuzi va sharbatlar uchun", 80, 2, 2023, kitchenId));
        products.add(new Product("Tefal Toster", "Yangi, 2 bo‘lakli; tezkor va sifatli qovurish", 50, 2, 2023, kitchenId));
        products.add(new Product("Samsung Pech", "Ishlatilgan, elektr; keng funksiyali, ta’mirlangan", 300, 3, 2020, kitchenId));
        products.add(new Product("LG Muzlatgich", "Yangi, bir eshikli; ixcham va tejamkor", 350, 3, 2023, kitchenId));
        products.add(new Product("Bosch Idish yuvish mashinasi", "Yangi, 12 kishilik; energiya tejovchi, jim", 500, 4, 2023, kitchenId));
        products.add(new Product("Philips Chovgum", "Yangi, 1.5 litr; tezkor suv qaynatish", 40, 2, 2023, kitchenId));
        products.add(new Product("Tefal Mikser", "Yangi, 500W; qaymoq va xamir tayyorlash uchun", 60, 2, 2023, kitchenId));
        products.add(new Product("Samsung Mikroto‘lqinli pech", "Yangi, 25 litr; zamonaviy va ixcham", 120, 2, 2023, kitchenId));
        products.add(new Product("Bosch Elektr plita", "Ishlatilgan, 4 zonali; sifatli va ishonchli", 350, 3, 2020, kitchenId));
        products.add(new Product("LG Idish yuvish mashinasi", "Yangi, 10 kishilik; tejamkor va zamonaviy", 450, 4, 2023, kitchenId));


        // MAN Products (11 items)
        products.add(new Product("MAN TGS 41.400", "Yangi, 6x4, dizel; qurilish va og‘ir yuk tashish uchun ideal", 95000, 5, 2023, manId));
        products.add(new Product("MAN TGA 33.360", "Ishlatilgan, 6x6, dizel; to‘liq ta’mirlangan, hujjatlar tayyor", 58000, 4, 2009, manId));
        products.add(new Product("MAN TGM 18.250", "Ishlatilgan, 4x2, dizel; shahar ichi yuk tashish uchun qulay", 42000, 3, 2020, manId));
        products.add(new Product("MAN TGX 18.440", "Ishlatilgan, 4x2, dizel; treyler va uzoq masofalar uchun mos", 67000, 6, 2017, manId));
        products.add(new Product("MAN TGS 33.480", "Ishlatilgan, 6x4, dizel; beton tashuvchi, yuqori samaradorlik", 73000, 7, 2019, manId));
        products.add(new Product("MAN LE 8.180", "Ishlatilgan, 4x2, dizel; ixcham, tejamkor va ishonchli", 21000, 3, 2007, manId));
        products.add(new Product("MAN TGM 15.290", "Yangi, 4x2, dizel; 15 tonna yuk ko‘tarish qobiliyati", 59000, 4, 2022, manId));
        products.add(new Product("MAN TGS 26.400", "Ishlatilgan, 6x4, dizel; suv tashish va maxsus vazifalar uchun", 78000, 6, 2021, manId));
        products.add(new Product("MAN TGS 35.480", "Yangi, 8x4, dizel; qurilish maydonlari uchun maxsus jihozlangan", 105000, 8, 2023, manId));
        products.add(new Product("MAN TGX 26.460", "Yangi, 6x4, dizel; uzoq masofali yuk tashish uchun premium", 97000, 7, 2022, manId));
        products.add(new Product("MAN TGL 12.220", "Yangi, 4x2, dizel; shahar va qisqa masofalar uchun tejamkor", 55000, 4, 2023, manId));

        // Isuzu Products (12 items)
        products.add(new Product("Isuzu NPR 75", "Yangi, 4x2, dizel; shahar yuk tashish uchun ishonchli", 38000, 4, 2023, isuzuId));
        products.add(new Product("Isuzu FVR 34", "Ishlatilgan, 4x2, dizel; og‘ir yuklar uchun mos", 45000, 5, 2020, isuzuId));
        products.add(new Product("Isuzu NQR 71", "Yangi, 4x2, dizel; o‘rtacha yuk sig‘imi, tejamkor", 35000, 3, 2022, isuzuId));
        products.add(new Product("Isuzu D-Max", "Ishlatilgan, 4x4, dizel; off-road va yuk tashish uchun", 30000, 4, 2019, isuzuId));
        products.add(new Product("Isuzu FSR 90", "Yangi, 4x2, dizel; uzoq masofalar uchun qulay", 48000, 5, 2023, isuzuId));
        products.add(new Product("Isuzu NLR 55", "Ishlatilgan, 4x2, dizel; engil yuk tashish uchun", 25000, 3, 2018, isuzuId));
        products.add(new Product("Isuzu CYZ 52", "Yangi, 6x4, dizel; og‘ir yuklar uchun maxsus", 65000, 6, 2022, isuzuId));
        products.add(new Product("Isuzu NPR 70", "Ishlatilgan, 4x2, dizel; ta’mirlangan, hujjatlar tayyor", 32000, 4, 2017, isuzuId));
        products.add(new Product("Isuzu EXR 52", "Yangi, 6x4, dizel; sanoat yuklari uchun mustahkam", 72000, 7, 2023, isuzuId));
        products.add(new Product("Isuzu NRR 55", "Ishlatilgan, 4x2, dizel; shahar logistikasiga mos", 28000, 3, 2020, isuzuId));
        products.add(new Product("Isuzu Giga CXZ", "Yangi, 6x4, dizel; og‘ir qurilish loyihalari uchun", 80000, 8, 2023, isuzuId));
        products.add(new Product("Isuzu ELF 5.5", "Ishlatilgan, 4x2, dizel; ixcham va tejamkor yuk mashinasi", 27000, 3, 2019, isuzuId));

        // Volvo Trucks Products (14 items)
        products.add(new Product("Volvo FH16 750", "Yangi, 6x4, dizel; og‘ir yuklar uchun premium model", 120000, 8, 2023, volvoId));
        products.add(new Product("Volvo FMX 460", "Ishlatilgan, 6x6, dizel; qurilish va off-road uchun", 85000, 6, 2019, volvoId));
        products.add(new Product("Volvo FH13 500", "Yangi, 4x2, dizel; uzoq masofali treyler uchun", 105000, 7, 2022, volvoId));
        products.add(new Product("Volvo FM11 370", "Ishlatilgan, 4x2, dizel; shahar va qisqa masofalar", 62000, 5, 2018, volvoId));
        products.add(new Product("Volvo FH16 650", "Yangi, 6x4, dizel; og‘ir sanoat yuklari uchun", 115000, 8, 2023, volvoId));
        products.add(new Product("Volvo FL 280", "Ishlatilgan, 4x2, dizel; engil yuk tashish uchun", 45000, 4, 2017, volvoId));
        products.add(new Product("Volvo FMX 500", "Yangi, 8x4, dizel; qurilish maydonlari uchun maxsus", 95000, 7, 2022, volvoId));
        products.add(new Product("Volvo FH13 460", "Ishlatilgan, 4x2, dizel; tejamkor va ishonchli", 70000, 6, 2020, volvoId));
        products.add(new Product("Volvo FE 320", "Yangi, 4x2, dizel; shahar logistikasiga mos", 68000, 5, 2023, volvoId));
        products.add(new Product("Volvo FM11 410", "Ishlatilgan, 6x4, dizel; qurilish va og‘ir yuklar", 78000, 6, 2019, volvoId));
        products.add(new Product("Volvo FH16 540", "Yangi, 6x4, dizel; uzoq masofalar uchun premium", 110000, 7, 2023, volvoId));
        products.add(new Product("Volvo FL 240", "Ishlatilgan, 4x2, dizel; ixcham va tejamkor", 40000, 3, 2016, volvoId));
        products.add(new Product("Volvo FM 420", "Yangi, 6x4, dizel; og‘ir yuklar va qurilish uchun", 90000, 7, 2023, volvoId));
        products.add(new Product("Volvo FH12 460", "Ishlatilgan, 4x2, dizel; uzoq masofalar uchun ishonchli", 65000, 6, 2018, volvoId));

        // Chevrolet Products (12 items)
        products.add(new Product("Chevrolet Malibu", "Yangi, benzin; zamonaviy dizayn, qulay salon", 27000, 5, 2023, chevroletId));
        products.add(new Product("Chevrolet Spark", "Ishlatilgan, benzin; shahar haydash uchun tejamkor", 10000, 3, 2018, chevroletId));
        products.add(new Product("Chevrolet Equinox", "Yangi, benzin; oilaviy SUV, keng salon", 32000, 5, 2023, chevroletId));
        products.add(new Product("Chevrolet Trax", "Ishlatilgan, benzin; ixcham SUV, tejamkor", 18000, 4, 2020, chevroletId));
        products.add(new Product("Chevrolet Camaro", "Yangi, benzin; sport avtomobil, yuqori tezlik", 45000, 6, 2023, chevroletId));
        products.add(new Product("Chevrolet Cobalt", "Yangi, benzin; byudjetli sedan, ishonchli", 15000, 4, 2022, chevroletId));
        products.add(new Product("Chevrolet Captiva", "Ishlatilgan, benzin; keng SUV, oilaviy sayohatlar uchun", 22000, 5, 2019, chevroletId));
        products.add(new Product("Chevrolet Tahoe", "Yangi, benzin; premium SUV, hashamatli salon", 60000, 7, 2023, chevroletId));
        products.add(new Product("Chevrolet Lacetti", "Ishlatilgan, benzin; tejamkor sedan, hujjatlar tayyor", 12000, 3, 2017, chevroletId));
        products.add(new Product("Chevrolet Traverse", "Yangi, benzin; katta oilalar uchun ideal SUV", 40000, 6, 2023, chevroletId));
        products.add(new Product("Chevrolet Blazer", "Yangi, benzin; zamonaviy SUV, dinamik dizayn", 35000, 5, 2023, chevroletId));
        products.add(new Product("Chevrolet Cruze", "Ishlatilgan, benzin; qulay sedan, yaxshi holat", 14000, 4, 2018, chevroletId));

        // Audi Products (13 items)
        products.add(new Product("Audi A4", "Yangi, benzin; premium sedan, zamonaviy texnologiyalar", 45000, 5, 2023, audiId));
        products.add(new Product("Audi Q5", "Ishlatilgan, benzin; keng SUV, qulaylik va ishonchlilik", 35000, 5, 2020, audiId));
        products.add(new Product("Audi A6", "Yangi, benzin; biznes-klass sedan, hashamatli dizayn", 60000, 6, 2023, audiId));
        products.add(new Product("Audi Q7", "Yangi, dizel; premium SUV, katta oilalar uchun", 75000, 7, 2023, audiId));
        products.add(new Product("Audi A3", "Ishlatilgan, benzin; ixcham sedan, shahar haydash uchun", 28000, 4, 2019, audiId));
        products.add(new Product("Audi Q3", "Yangi, benzin; zamonaviy SUV, yoshlar uchun ideal", 40000, 5, 2023, audiId));
        products.add(new Product("Audi A8", "Ishlatilgan, benzin; hashamatli sedan, yuqori komfort", 65000, 6, 2020, audiId));
        products.add(new Product("Audi e-tron", "Yangi, elektr; ekologik toza SUV, zamonaviy", 80000, 7, 2023, audiId));
        products.add(new Product("Audi A5", "Ishlatilgan, benzin; sport kupe, dinamik haydash", 38000, 5, 2018, audiId));
        products.add(new Product("Audi Q8", "Yangi, benzin; premium SUV, eng yangi texnologiyalar", 85000, 7, 2023, audiId));
        products.add(new Product("Audi A7", "Yangi, benzin; sportback dizayn, hashamatli salon", 70000, 6, 2023, audiId));
        products.add(new Product("Audi SQ5", "Ishlatilgan, benzin; sport SUV, yuqori quvvat", 45000, 5, 2020, audiId));
        products.add(new Product("Audi TT", "Yangi, benzin; sport kupe, zamonaviy va tezkor", 55000, 5, 2023, audiId));

        // Volkswagen Products (12 items)
        products.add(new Product("Volkswagen Polo", "Yangi, benzin; ixcham sedan, shahar uchun ideal", 20000, 4, 2023, volkswagenId));
        products.add(new Product("Volkswagen Tiguan", "Ishlatilgan, benzin; keng SUV, oilaviy sayohatlar", 30000, 5, 2020, volkswagenId));
        products.add(new Product("Volkswagen Passat", "Yangi, benzin; biznes-klass sedan, qulay salon", 35000, 5, 2023, volkswagenId));
        products.add(new Product("Volkswagen Golf", "Ishlatilgan, benzin; sport hatchback, dinamik", 22000, 4, 2019, volkswagenId));
        products.add(new Product("Volkswagen Touareg", "Yangi, dizel; premium SUV, off-road qobiliyati", 60000, 6, 2023, volkswagenId));
        products.add(new Product("Volkswagen Jetta", "Yangi, benzin; tejamkor sedan, zamonaviy dizayn", 25000, 4, 2023, volkswagenId));
        products.add(new Product("Volkswagen T-Roc", "Ishlatilgan, benzin; zamonaviy SUV, yoshlar uchun", 28000, 5, 2020, volkswagenId));
        products.add(new Product("Volkswagen Arteon", "Yangi, benzin; premium sedan, hashamatli salon", 45000, 6, 2023, volkswagenId));
        products.add(new Product("Volkswagen T-Cross", "Ishlatilgan, benzin; ixcham SUV, shahar haydash", 23000, 4, 2019, volkswagenId));
        products.add(new Product("Volkswagen ID.4", "Yangi, elektr; ekologik toza SUV, zamonaviy", 50000, 6, 2023, volkswagenId));
        products.add(new Product("Volkswagen Golf GTI", "Yangi, benzin; sport hatchback, yuqori tezlik", 40000, 5, 2023, volkswagenId));
        products.add(new Product("Volkswagen Amarok", "Ishlatilgan, dizel; mustahkam pikap, off-road", 35000, 5, 2020, volkswagenId));

        // Toyota Products (13 items)
        products.add(new Product("Toyota Corolla", "Yangi, benzin; ishonchli sedan, tejamkor", 25000, 4, 2023, toyotaId));
        products.add(new Product("Toyota Camry", "Ishlatilgan, benzin; biznes-klass sedan, qulay", 32000, 5, 2020, toyotaId));
        products.add(new Product("Toyota RAV4", "Yangi, benzin; keng SUV, oilaviy sayohatlar", 38000, 5, 2023, toyotaId));
        products.add(new Product("Toyota Hilux", "Ishlatilgan, dizel; off-road pikap, mustahkam", 35000, 5, 2019, toyotaId));
        products.add(new Product("Toyota Land Cruiser", "Yangi, dizel; premium SUV, off-road qobiliyati", 80000, 7, 2023, toyotaId));
        products.add(new Product("Toyota Yaris", "Yangi, benzin; ixcham hatchback, shahar uchun", 20000, 4, 2023, toyotaId));
        products.add(new Product("Toyota Highlander", "Ishlatilgan, benzin; katta SUV, keng salon", 45000, 6, 2020, toyotaId));
        products.add(new Product("Toyota Prius", "Yangi, gibrid; ekologik toza sedan, tejamkor", 30000, 5, 2023, toyotaId));
        products.add(new Product("Toyota Fortuner", "Yangi, dizel; mustahkam SUV, off-road uchun", 50000, 6, 2023, toyotaId));
        products.add(new Product("Toyota C-HR", "Ishlatilgan, benzin; zamonaviy SUV, yoshlar uchun", 28000, 5, 2019, toyotaId));
        products.add(new Product("Toyota Tacoma", "Yangi, benzin; mustahkam pikap, off-road uchun", 40000, 5, 2023, toyotaId));
        products.add(new Product("Toyota Supra", "Yangi, benzin; sport avtomobil, yuqori tezlik", 60000, 6, 2023, toyotaId));
        products.add(new Product("Toyota Sienna", "Ishlatilgan, benzin; oilaviy miniven, keng salon", 35000, 5, 2020, toyotaId));

        // Kvartira (ijara)
        products.add(new Product("2 xonali Chilonzor", "Toshkent, Chilanzor; yangi ta’mirlangan, mebel bilan", 400, 3, 2023, rentKvId));
        products.add(new Product("3 xonali Novza", "Toshkent, Novza; keng va yorug‘, metro yaqin", 600, 4, 2023, rentKvId));
        products.add(new Product("1 xonali Yunusobod", "Toshkent, Yunusobod; ixcham, tejamkor, mebel bilan", 260, 2, 2023, rentKvId));
        products.add(new Product("3 xonali Shayxontohur", "Toshkent, Shayxontohur; hashamatli, keng teras", 800, 5, 2023, rentKvId));
        products.add(new Product("2 xonali Sergeli", "Toshkent, Sergeli; yangi binoda, hujjatlar tayyor", 350, 3, 2023, rentKvId));
        products.add(new Product("3 xonali Yakkasaroy", "Toshkent, Yakkasaroy; oilaviy, qulay joylashuv", 550, 4, 2023, rentKvId));
        products.add(new Product("1 xonali Olmazor", "Toshkent, Olmazor; arzon va qulay, metro yaqin", 200, 2, 2023, rentKvId));
        products.add(new Product("2 xonali Uchtepa", "Toshkent, Uchtepa; zamonaviy ta’mir, mebel bilan", 450, 3, 2023, rentKvId));
        products.add(new Product("1 xonali Bektemir", "Toshkent, Bektemir; keng va shinam, metro yaqin", 500, 4, 2023, rentKvId));
        products.add(new Product("2 xonali Yashnobod", "Toshkent, Yashnobod; yangi ta’mir, qulay joylashuv", 420, 3, 2023, rentKvId));

// Hovli uy (ijara)
        products.add(new Product("3 xona Bektemir", "Toshkent, Bektemir; keng hovli, bog‘li, garaj", 700, 4, 2023, rentHovliId));
        products.add(new Product("4 xona Chilonzor", "Toshkent, Chilanzor; yangi ta’mir, oilaviy", 900, 5, 2023, rentHovliId));
        products.add(new Product("5 xona Sergeli", "Toshkent, Sergeli; ixcham, bog‘ va garaj", 500, 3, 2023, rentHovliId));
        products.add(new Product("5 xona Novza", "Toshkent, Novza; hashamatli, basseyn", 1200, 6, 2023, rentHovliId));
        products.add(new Product("3 xona Yashnobod", "Toshkent, Yashnobod; qulay joylashuv, ta’mirlangan", 650, 4, 2023, rentHovliId));
        products.add(new Product("4 xona Olmazor", "Toshkent, Olmazor; keng hovli, oilaviy", 850, 5, 2023, rentHovliId));
        products.add(new Product("2 xona Yakkasaroy", "Toshkent, Yakkasaroy; tejamkor, bog‘li", 450, 3, 2023, rentHovliId));
        products.add(new Product("3 xona Uchtepa", "Toshkent, Uchtepa; zamonaviy, garaj bilan", 700, 4, 2023, rentHovliId));
        products.add(new Product("5 xona Chilonzor", "Toshkent, Chilanzor; premium, katta basseyn", 1300, 6, 2023, rentHovliId));
        products.add(new Product("2 xona Sergeli", "Toshkent, Sergeli; shinam va qulay, bog‘li", 600, 4, 2023, rentHovliId));
        products.add(new Product("4 xona Bektemir", "Toshkent, Bektemir; keng va zamonaviy, garaj", 950, 5, 2023, rentHovliId));
        products.add(new Product("2 xona Yashnobod", "Toshkent, Yashnobod; tejamkor, kichik bog‘", 400, 3, 2023, rentHovliId));

// Studio (ijara)
        products.add(new Product("Chilonzor studio", "Toshkent, Chilanzor; zamonaviy dizayn, mebel bilan", 350, 2, 2023, rentStudioId));
        products.add(new Product("Novza studio", "Toshkent, Novza; keng va yorug‘, metro yaqin", 500, 3, 2023, rentStudioId));
        products.add(new Product("Yunusobod studio", "Toshkent, Yunusobod; ixcham, tejamkor, ta’mirlangan", 250, 2, 2023, rentStudioId));
        products.add(new Product("Shayxontohur studio", "Toshkent, Shayxontohur; hashamatli, yangi binoda", 600, 4, 2023, rentStudioId));
        products.add(new Product("Sergeli studio", "Toshkent, Sergeli; zamonaviy texnologiyalar, mebel", 400, 3, 2023, rentStudioId));
        products.add(new Product("Yakkasaroy studio", "Toshkent, Yakkasaroy; qulay va shinam, metro yaqin", 350, 2, 2023, rentStudioId));
        products.add(new Product("Olmazor studio", "Toshkent, Olmazor; yangi ta’mir, qulay joylashuv", 450, 3, 2023, rentStudioId));
        products.add(new Product("Uchtepa studio", "Toshkent, Uchtepa; tejamkor, mebel bilan", 300, 2, 2023, rentStudioId));
        products.add(new Product("Elite studio", "Toshkent, Chilanzor; premium dizayn, keng balkon", 550, 3, 2023, rentStudioId));
        products.add(new Product("Yashnobod studio", "Toshkent, Yashnobod; zamonaviy va qulay, mebel bilan", 380, 2, 2023, rentStudioId));

// Kvartira (sotuv)
        products.add(new Product("2 xonali Novza", "Toshkent, Novza; keng va yorug‘, metro yaqin", 75000, 4, 2023, saleKvId));
        products.add(new Product("2 xonali Yunusobod", "Toshkent, Yunusobod; ixcham, tejamkor", 35000, 2, 2023, saleKvId));
        products.add(new Product("4 xonali Shayxontohur", "Toshkent, Shayxontohur; hashamatli, keng teras", 100000, 5, 2023, saleKvId));
        products.add(new Product("3 xonali Sergeli", "Toshkent, Sergeli; yangi binoda, hujjatlar tayyor", 45000, 3, 2023, saleKvId));
        products.add(new Product("4 xonali Yakkasaroy", "Toshkent, Yakkasaroy; oilaviy, qulay joylashuv", 70000, 4, 2023, saleKvId));
        products.add(new Product("2 xonali Olmazor", "Toshkent, Olmazor; arzon, metro yaqin", 30000, 2, 2023, saleKvId));
        products.add(new Product("1 xonali Uchtepa", "Toshkent, Uchtepa; zamonaviy ta’mir, qulay", 55000, 3, 2023, saleKvId));
        products.add(new Product("3 xonali Bektemir", "Toshkent, Bektemir; keng va shinam, yangi binoda", 72000, 4, 2023, saleKvId));
        products.add(new Product("4 xonali Yashnobod", "Toshkent, Yashnobod; premium, katta balkon", 95000, 5, 2023, saleKvId));
        products.add(new Product("1 xonali Chilonzor", "Toshkent, Chilanzor; tejamkor, ta’mirlangan", 48000, 3, 2023, saleKvId));
        products.add(new Product("1 xonali Sergeli", "Toshkent, Sergeli; ixcham va qulay, hujjatlar tayyor", 32000, 2, 2023, saleKvId));

// Hovli uy (sotuv)
        products.add(new Product("1 xona Bektemir", "Toshkent, Bektemir; keng hovli, bog‘li, garaj", 90000, 4, 2023, saleHovliId));
        products.add(new Product("2 xona Chilonzor", "Toshkent, Chilanzor; yangi ta’mir, oilaviy", 120000, 5, 2023, saleHovliId));
        products.add(new Product("6 xona Sergeli", "Toshkent, Sergeli; ixcham, bog‘ va garaj", 70000, 3, 2023, saleHovliId));
        products.add(new Product("4 xona Novza", "Toshkent, Novza; hashamatli, basseyn", 150000, 6, 2023, saleHovliId));
        products.add(new Product("3 xona Yashnobod 1", "Toshkent, Yashnobod; qulay joylashuv, ta’mirlangan", 85000, 4, 2023, saleHovliId));
        products.add(new Product("1 xona Olmazor", "Toshkent, Olmazor; keng hovli, oilaviy", 140000, 5, 2023, saleHovliId));
        products.add(new Product("4 xona Yakkasaroy", "Toshkent, Yakkasaroy; tejamkor, bog‘li", 65000, 3, 2023, saleHovliId));
        products.add(new Product("3 xona Uchtepa 23", "Toshkent, Uchtepa; zamonaviy, garaj bilan", 95000, 4, 2023, saleHovliId));
        products.add(new Product("5 xona Chilonzor 1", "Toshkent, Chilanzor; premium, katta basseyn", 140000, 6, 2023, saleHovliId));
        products.add(new Product("4 xona Sergeli", "Toshkent, Sergeli; keng va shinam, bog‘li", 110000, 5, 2023, saleHovliId));
        products.add(new Product("3 xona Yashnobod 2", "Toshkent, Yashnobod; zamonaviy ta’mir, qulay", 88000, 4, 2023, saleHovliId));
        products.add(new Product("2 xona Bektemir", "Toshkent, Bektemir; tejamkor, kichik bog‘", 60000, 3, 2023, saleHovliId));
        products.add(new Product("1 xona Novza", "Toshkent, Novza; hashamatli, keng hovli", 130000, 5, 2023, saleHovliId));

// Villa (sotuv)
        products.add(new Product("5 xona Novza 3", "Toshkent, Novza; hashamatli, basseyn va bog‘", 250000, 7, 2023, saleVillaId));
        products.add(new Product("1 xona Chilonzor", "Toshkent, Chilanzor; zamonaviy dizayn, keng hovli", 200000, 6, 2023, saleVillaId));
        products.add(new Product("6 xona Yashnobod", "Toshkent, Yashnobod; premium, katta basseyn", 300000, 8, 2023, saleVillaId));
        products.add(new Product("3 xona Yakkasaroy", "Toshkent, Yakkasaroy; shinam, bog‘ va garaj", 150000, 5, 2023, saleVillaId));
        products.add(new Product("5 xona Olmazor", "Toshkent, Olmazor; hashamatli, zamonaviy jihozlar", 270000, 7, 2023, saleVillaId));
        products.add(new Product("4 xona Uchtepa", "Toshkent, Uchtepa; keng teras, bog‘li", 180000, 6, 2023, saleVillaId));
        products.add(new Product("3 xona Sergeli", "Toshkent, Sergeli; tejamkor, zamonaviy ta’mir", 140000, 5, 2023, saleVillaId));
        products.add(new Product("6 xona Bektemir", "Toshkent, Bektemir; premium, katta hovli va basseyn", 320000, 8, 2023, saleVillaId));
        products.add(new Product("4 xona Chilonzor 3", "Toshkent, Chilanzor; shinam va qulay, bog‘li", 190000, 6, 2023, saleVillaId));
        products.add(new Product("5 xona Yashnobod", "Toshkent, Yashnobod; hashamatli, keng basseyn", 260000, 7, 2023, saleVillaId));

        // iPhone Products (10 items)
        products.add(new Product("iPhone 14 Pro", "Yangi, 128GB; zamonaviy kamera, tezkor protsessor", 1000, 5, 2023, iphoneId));
        products.add(new Product("iPhone 13", "Ishlatilgan, 128GB; yaxshi holat, original", 600, 4, 2021, iphoneId));
        products.add(new Product("iPhone 14", "Yangi, 256GB; keng ekran, uzoq batareya", 900, 5, 2023, iphoneId));
        products.add(new Product("iPhone 12 Pro", "Ishlatilgan, 256GB; ajoyib kamera, ta’mirlangan", 550, 4, 2020, iphoneId));
        products.add(new Product("iPhone 15", "Yangi, 128GB; yangi dizayn, yuqori samaradorlik", 1100, 5, 2023, iphoneId));
        products.add(new Product("iPhone 11", "Ishlatilgan, 64GB; tejamkor, ishonchli", 400, 3, 2019, iphoneId));
        products.add(new Product("iPhone 14 Pro Max", "Yangi, 256GB; premium model, katta ekran", 1200, 6, 2023, iphoneId));
        products.add(new Product("iPhone 13 Mini", "Ishlatilgan, 128GB; ixcham va qulay", 500, 4, 2021, iphoneId));
        products.add(new Product("iPhone 15 Pro", "Yangi, 256GB; eng yangi model, yuqori sifat", 1300, 6, 2023, iphoneId));
        products.add(new Product("iPhone 9", "Ishlatilgan, 128GB; yaxshi holat, original", 450, 4, 2020, iphoneId));

        // Samsung Products (12 items)
        products.add(new Product("Galaxy S23", "Yangi, 128GB; tezkor, ajoyib kamera", 800, 5, 2023, samsungId));
        products.add(new Product("Galaxy A53", "Ishlatilgan, 128GB; yaxshi holat, tejamkor", 300, 4, 2022, samsungId));
        products.add(new Product("Galaxy Z Fold 5", "Yangi, 256GB; buklanadigan ekran, premium", 1500, 6, 2023, samsungId));
        products.add(new Product("Galaxy S22", "Ishlatilgan, 128GB; yuqori sifatli kamera", 600, 5, 2022, samsungId));
        products.add(new Product("Galaxy A34", "Yangi, 128GB; byudjetli, ishonchli", 350, 4, 2023, samsungId));
        products.add(new Product("Galaxy Note 20", "Ishlatilgan, 256GB; qalamli, biznes uchun", 700, 4, 2020, samsungId));
        products.add(new Product("Galaxy Z Flip 5", "Yangi, 256GB; zamonaviy dizayn, buklanadigan", 1000, 5, 2023, samsungId));
        products.add(new Product("Galaxy A14", "Yangi, 64GB; arzon va sifatli, shahar uchun", 200, 3, 2023, samsungId));
        products.add(new Product("Galaxy S23 Ultra", "Yangi, 256GB; premium model, ajoyib kamera", 900, 5, 2023, samsungId));
        products.add(new Product("Galaxy A73", "Ishlatilgan, 128GB; yaxshi holat, keng ekran", 400, 4, 2022, samsungId));
        products.add(new Product("Galaxy Z Fold 4", "Ishlatilgan, 256GB; buklanadigan, premium", 1200, 6, 2022, samsungId));
        products.add(new Product("Galaxy A54", "Yangi, 128GB; zamonaviy va tejamkor", 380, 4, 2023, samsungId));

        // Xiaomi Products (12 items)
        products.add(new Product("Redmi 13 Pro", "Yangi, 256GB; premium kamera, tezkor", 700, 5, 2023, xiaomiId));
        products.add(new Product("Redmi Note 11", "Ishlatilgan, 128GB; yaxshi holat, tejamkor", 250, 4, 2022, xiaomiId));
        products.add(new Product("Poco X5", "Yangi, 128GB; o‘yinlar uchun ideal, tezkor", 350, 4, 2023, xiaomiId));
        products.add(new Product("Redmi 12", "Ishlatilgan, 128GB; yuqori sifat, ta’mirlangan", 400, 4, 2022, xiaomiId));
        products.add(new Product("Redmi 14", "Yangi, 128GB; byudjetli, keng ekran", 200, 3, 2023, xiaomiId));
        products.add(new Product("Mi 11 Lite", "Ishlatilgan, 128GB; engil va zamonaviy", 300, 4, 2021, xiaomiId));
        products.add(new Product("Redmi 13", "Yangi, 256GB; premium model, ajoyib kamera", 600, 5, 2023, xiaomiId));
        products.add(new Product("Poco M5", "Yangi, 64GB; arzon va ishonchli", 190, 3, 2023, xiaomiId));
        products.add(new Product("Redmi 12 Pro", "Yangi, 256GB; tezkor va sifatli kamera", 450, 4, 2023, xiaomiId));
        products.add(new Product("Poco F5", "Yangi, 128GB; o‘yinlar uchun maxsus, tezkor", 400, 4, 2023, xiaomiId));
        products.add(new Product("Redmi Note 12 Pro", "Ishlatilgan, 256GB; premium, yaxshi holat", 550, 5, 2022, xiaomiId));
        products.add(new Product("Redmi 10", "Ishlatilgan, 128GB; byudjetli, yaxshi holat", 220, 3, 2022, xiaomiId));

        // Realme Products (10 items)
        products.add(new Product("Realme GT 2 Pro", "Yangi, 256GB; tezkor, premium dizayn", 600, 5, 2023, realmeId));
        products.add(new Product("Realme Narzo 50", "Ishlatilgan, 128GB; byudjetli, yaxshi holat", 200, 3, 2022, realmeId));
        products.add(new Product("Realme 10 Pro", "Yangi, 128GB; keng ekran, tezkor protsessor", 350, 4, 2023, realmeId));
        products.add(new Product("Realme C35", "Yangi, 64GB; arzon va ishonchli, shahar uchun", 150, 3, 2023, realmeId));
        products.add(new Product("Realme 7", "Ishlatilgan, 128GB; yaxshi kamera, ta’mirlangan", 250, 4, 2022, realmeId));
        products.add(new Product("Realme GT Neo 3", "Yangi, 256GB; o‘yinlar uchun ideal, tezkor", 500, 5, 2023, realmeId));
        products.add(new Product("Realme C55", "Yangi, 128GB; byudjetli, keng batareya", 200, 3, 2023, realmeId));
        products.add(new Product("Realme 8 Pro", "Ishlatilgan, 128GB; yaxshi kamera, tejamkor", 220, 4, 2021, realmeId));
        products.add(new Product("Realme 10", "Yangi, 128GB; zamonaviy va sifatli", 300, 4, 2023, realmeId));
        products.add(new Product("Realme Narzo 50i", "Ishlatilgan, 64GB; byudjetli, yaxshi holat", 180, 3, 2022, realmeId));

        // Infinix Products (10 items)
        products.add(new Product("Infinix Zero Ultra", "Yangi, 256GB; tezkor, ajoyib kamera", 400, 4, 2023, infinixId));
        products.add(new Product("Infinix Hot 20", "Yangi, 128GB; byudjetli, keng ekran", 150, 3, 2023, infinixId));
        products.add(new Product("Infinix Note 12", "Ishlatilgan, 128GB; yaxshi holat, tejamkor", 200, 3, 2022, infinixId));
        products.add(new Product("Infinix Smart 7", "Yangi, 64GB; arzon va ishonchli", 120, 2, 2023, infinixId));
        products.add(new Product("Infinix Zero 20", "Yangi, 128GB; zamonaviy dizayn, yaxshi kamera", 300, 4, 2023, infinixId));
        products.add(new Product("Infinix Hot 10", "Ishlatilgan, 64GB; tejamkor, yaxshi holat", 130, 3, 2021, infinixId));
        products.add(new Product("Infinix 11 Pro", "Yangi, 128GB; o‘yinlar uchun ideal, tezkor", 250, 4, 2023, infinixId));
        products.add(new Product("Infinix Smart 6", "Ishlatilgan, 64GB; arzon va qulay", 100, 2, 2022, infinixId));
        products.add(new Product("Infinix Hot 30", "Yangi, 128GB; byudjetli, katta ekran", 170, 3, 2023, infinixId));
        products.add(new Product("Infinix Note 10", "Ishlatilgan, 128GB; yaxshi holat, tejamkor", 190, 3, 2022, infinixId));

        // Sony Ericsson Products (9 items)
        products.add(new Product("Xperia Arc", "Ishlatilgan, 512MB; retro model, yaxshi holat", 50, 2, 2011, sonyEricssonId));
        products.add(new Product("Xperia Play", "Ishlatilgan, 512MB; o‘yinlar uchun, ta’mirlangan", 60, 2, 2011, sonyEricssonId));
        products.add(new Product("Xperia Mini", "Ishlatilgan, 512MB; ixcham, retro dizayn", 40, 2, 2011, sonyEricssonId));
        products.add(new Product("W995", "Ishlatilgan, 128MB; klassik model, yaxshi holat", 30, 2, 2009, sonyEricssonId));
        products.add(new Product("Xperia Neo", "Ishlatilgan, 512MB; retro, yaxshi kamera", 50, 2, 2011, sonyEricssonId));
        products.add(new Product("K850i", "Ishlatilgan, 64MB; klassik, yaxshi holat", 25, 1, 2007, sonyEricssonId));
        products.add(new Product("Xperia Ray", "Ishlatilgan, 512MB; ixcham va qulay", 45, 2, 2011, sonyEricssonId));
        products.add(new Product("W810i", "Ishlatilgan, 64MB; retro telefon, yaxshi holat", 20, 1, 2006, sonyEricssonId));
        products.add(new Product("C902", "Ishlatilgan, 64MB; klassik model, yaxshi holat", 30, 1, 2008, sonyEricssonId));

        // Nokia Products (9 items)
        products.add(new Product("Nokia 3310", "Ishlatilgan, 2G; klassik model, yaxshi holat", 30, 1, 2017, nokiaId));
        products.add(new Product("Nokia 8110", "Yangi, 4G; retro dizayn, zamonaviy", 50, 2, 2018, nokiaId));
        products.add(new Product("Nokia Lumia 520", "Ishlatilgan, 8GB; Windows Phone, yaxshi holat", 40, 2, 2013, nokiaId));
        products.add(new Product("Nokia 105", "Yangi, 2G; oddiy telefon, uzoq batareya", 20, 1, 2023, nokiaId));
        products.add(new Product("Nokia 6300", "Ishlatilgan, 4G; zamonaviy klassik model", 60, 2, 2021, nokiaId));
        products.add(new Product("Nokia 6600", "Yangi, 64MB; retro model, yaxshi holat", 25, 1, 2003, nokiaId));
        products.add(new Product("Nokia 5310", "Ishlatilgan, 2G; musiqa telefoni, ta’mirlangan", 30, 1, 2020, nokiaId));
        products.add(new Product("Nokia 150", "Yangi, 2G; oddiy va ishonchli telefon", 25, 1, 2023, nokiaId));
        products.add(new Product("Nokia 2660 Flip", "Yangi, 4G; zamonaviy flip telefon, uzoq batareya", 70, 2, 2023, nokiaId));

        // Samsung Flippy
        products.add(new Product("Samsung GT-E1272", "Bukiladigan, tugmali, ikki SIM; klassik va ishonchli model", 35, 4, 2015, samsungFlippyId));
        products.add(new Product("Samsung GT-C3520", "Tugmali, kamera, FM radio; bukiladigan dizayn bilan", 45, 5, 2013, samsungFlippyId));
        products.add(new Product("Samsung SGH-E1195", "Mini hajm, kuchli signal, uzoq batareya; faqat qo‘ng‘iroq va SMS uchun", 25, 3, 2012, samsungFlippyId));
        products.add(new Product("Samsung SGH-C450", "Oddiy tugmali telefon; ixcham va mustahkam korpus", 28, 4, 2011, samsungFlippyId));
        products.add(new Product("Samsung GT-S3600", "Tugmali telefon; bukiladigan metall dizayn, FM va MP3", 50, 6, 2010, samsungFlippyId));
        products.add(new Product("Samsung SGH-X495", "OLED ekranga ega tugmali flip telefon; kichik va qulay", 32, 3, 2009, samsungFlippyId));
        products.add(new Product("Samsung SGH-T409", "Bukiladigan, rangli displey; oddiy foydalanish uchun ideal", 29, 5, 2008, samsungFlippyId));
        products.add(new Product("Samsung SGH-A177", "Tugmali, uzoq batareya, oddiy interfeys; birinchi telefon sifatida mos", 33, 4, 2009, samsungFlippyId));
        products.add(new Product("Samsung SGH-A437", "Qora korpus, yaxshi eshitilish sifati va signal", 31, 6, 2008, samsungFlippyId));
        products.add(new Product("Samsung GT-E1150", "Tugmali, faqat qo‘ng‘iroq va SMS; energiyani tejovchi", 22, 3, 2010, samsungFlippyId));
        products.add(new Product("Samsung SGH-E1360", "Sodda dizayn, mustahkam korpus, uzoq umrli batareya", 27, 4, 2011, samsungFlippyId));
        products.add(new Product("Samsung SGH-T229", "Bukiladigan, FM radio, rangli displey; oddiy ishlatish uchun", 36, 5, 2012, samsungFlippyId));
        products.add(new Product("Samsung SGH-T139", "Tugmali, kichik, byudjetga mos model", 24, 3, 2010, samsungFlippyId));
        products.add(new Product("Samsung GT-E1182", "Tugmali, ikki SIM, uzoq batareya; past narxli variant", 26, 4, 2013, samsungFlippyId));
        products.add(new Product("Samsung GT-C3595", "Bukiladigan, kamera, FM radio, microSD qo‘llab-quvvatlashi", 39, 6, 2014, samsungFlippyId));
        products.add(new Product("Samsung GT-S4010C", "Tugmali, mustahkam, ishlatish uchun oson", 30, 5, 2011, samsungFlippyId));
    }

    public List<Product> getAll() {
        return products.stream()
                .filter(Product::isActive)
                .collect(Collectors.toList());
    }


    @Override
    public void add(Product product) throws IOException {
    }

    @Override
    public Product get(UUID id) {
        return null;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public void delete(Product product) {

    }

    private void save() throws IOException {
        FileUtils.writeToJson(FILE_NAME, products);
    }

    private List<Product> loadFromFile() throws IOException {
        return FileUtils.readFromJson(FILE_NAME, Product.class);
    }
}

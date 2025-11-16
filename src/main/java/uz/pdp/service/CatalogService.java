package uz.pdp.service;

import uz.pdp.base.BaseService;
import uz.pdp.model.Catalog;
import uz.pdp.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CatalogService implements BaseService<Catalog> {

    private static final String FILE_NAME_CATALOG = "catalogs.xml";
    public static final UUID ROOT_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private static final List<Catalog> catalogs = new ArrayList<>();

    public CatalogService() {
        // main section
    }

    public List<Catalog> getAll() {
        return catalogs.stream()
                .filter(Catalog::isActive)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void add(Catalog catalog) throws IOException {

    }

    @Override
    public Catalog get(UUID id) {
        return null;
    }

    @Override
    public boolean update(Catalog catalog) {
        return false;
    }

    @Override
    public void delete(Catalog catalog) {

    }

    private void save() throws IOException {
        FileUtils.writeToXml(FILE_NAME_CATALOG, catalogs);
    }

    private List<Catalog> loadFromFile() throws IOException {
        return FileUtils.readFromXml(FILE_NAME_CATALOG, Catalog.class);
    }

    static {
        Catalog cars = new Catalog("🚗 Mashinalar", ROOT_ID);
        Catalog houses = new Catalog("🏠 Uylar", ROOT_ID);
        Catalog tech = new Catalog("🛠️ Texnikalar", ROOT_ID);
        Catalog phones = new Catalog("📱 Telefonlar", ROOT_ID);

        catalogs.add(cars);
        catalogs.add(houses);
        catalogs.add(tech);
        catalogs.add(phones);

        // Mashinalar
        Catalog trucks = new Catalog("🚛 Yuk mashinalar", cars.getId());
        Catalog lightCars = new Catalog("🚗 Yengil mashinalar", cars.getId());
        catalogs.add(trucks);
        catalogs.add(lightCars);

        // Uylar
        Catalog rent = new Catalog("Ijaraga beriladigan", houses.getId());
        Catalog forSale = new Catalog("Sotiladigan", houses.getId());
        catalogs.add(rent);
        catalogs.add(forSale);

        // Texnikalar
        Catalog homeAppliances = new Catalog("Office texnikasi", tech.getId());
        Catalog kitchenAppliances = new Catalog("Oshxona texnikasi", tech.getId());
        catalogs.add(homeAppliances);
        catalogs.add(kitchenAppliances);

        // Telefonlar
        Catalog smartphones = new Catalog("📱 Smartfonlar", phones.getId());
        Catalog buttonPhones = new Catalog("☎ Tugmali telefonlar", phones.getId());
        catalogs.add(smartphones);
        catalogs.add(buttonPhones);

        catalogs.add(new Catalog("MAN", trucks.getId()));
        catalogs.add(new Catalog("Isuzu", trucks.getId()));
        catalogs.add(new Catalog("Volvo Trucks", trucks.getId()));

        catalogs.add(new Catalog("Chevrolet", lightCars.getId()));
        catalogs.add(new Catalog("Audi", lightCars.getId()));
        catalogs.add(new Catalog("Volkswagen (VW)", lightCars.getId()));
        catalogs.add(new Catalog("Toyota", lightCars.getId()));

        catalogs.add(new Catalog("Kvartira (ijara)", rent.getId()));
        catalogs.add(new Catalog("Hovli uy (ijara)", rent.getId()));
        catalogs.add(new Catalog("Studio (ijara)", rent.getId()));

        catalogs.add(new Catalog("Kvartira (sotuv)", forSale.getId()));
        catalogs.add(new Catalog("Hovli uy (sotuv)", forSale.getId()));
        catalogs.add(new Catalog("Villa (sotuv)", forSale.getId()));

        catalogs.add(new Catalog("Printer", homeAppliances.getId()));
        catalogs.add(new Catalog("Proyektor", homeAppliances.getId()));
        catalogs.add(new Catalog("Paper shredder", homeAppliances.getId()));

        catalogs.add(new Catalog("Muzlatkich", kitchenAppliances.getId()));
        catalogs.add(new Catalog("Blender", kitchenAppliances.getId()));
        catalogs.add(new Catalog("Multivarka", kitchenAppliances.getId()));

        catalogs.add(new Catalog("iPhone", smartphones.getId()));
        catalogs.add(new Catalog("Samsung", smartphones.getId()));
        catalogs.add(new Catalog("Xiaomi", smartphones.getId()));
        catalogs.add(new Catalog("Realme", smartphones.getId()));
        catalogs.add(new Catalog("Infinix", smartphones.getId()));

        catalogs.add(new Catalog("Sony Ericsson", buttonPhones.getId()));
        catalogs.add(new Catalog("Nokia", buttonPhones.getId()));
        catalogs.add(new Catalog("Samsung flippy", buttonPhones.getId()));
    }

}

package uz.pdp.bot.memory;

import uz.pdp.model.Catalog;
import java.util.*;

import static uz.pdp.service.CatalogService.ROOT_ID;

public class CatalogMemoryService {
    private final Map<UUID, List<Catalog>> parentIdToChildren = new HashMap<>();
    private final Map<String, Catalog> nameToCatalog = new HashMap<>();

    public CatalogMemoryService(List<Catalog> categories) {
        loadMaps(categories);
    }

    private void loadMaps(List<Catalog> catalogs) {
        for (Catalog category : catalogs) {
            if (!category.isActive()) continue;
            parentIdToChildren
                     .computeIfAbsent(category.getParentId(), k -> new ArrayList<>())
                    .add(category);

            nameToCatalog.put(category.getName().toLowerCase(), category);
        }
    }

    public List<Catalog> getChildren(UUID parentId) {
        return parentIdToChildren.getOrDefault(parentId, new ArrayList<>());
    }

    public Catalog getByName(String name) {
        return nameToCatalog.get(name.toLowerCase());
    }

    public List<Catalog> getRootCategories() {
        return getChildren(ROOT_ID);
    }
}



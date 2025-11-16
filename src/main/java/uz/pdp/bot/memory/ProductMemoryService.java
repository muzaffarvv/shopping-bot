package uz.pdp.bot.memory;

import uz.pdp.model.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductMemoryService {
    private Map<UUID, List<Product>> productMap = new HashMap<>();
    private Map<UUID , Product> stringProductMap = new HashMap<>();

    public ProductMemoryService(List<Product> products) {
        loadMaps(products);
    }

    private void loadMaps(List<Product> products) {

        this.productMap = products.stream()
                .collect(Collectors.groupingBy(Product::getCategoryId));
        this.stringProductMap = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
        }


    public List<Product> getProductsByCategoryId(UUID categoryId) {
        return productMap.get(categoryId);
    }

    public Product getProductById(UUID productId) {
        return stringProductMap.get(productId);
    }


}

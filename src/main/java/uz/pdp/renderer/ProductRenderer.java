package uz.pdp.renderer;

import uz.pdp.model.Product;

public final class ProductRenderer {

    public static String render(Product product, double totalPrice) {
        return """
                🔰 %s
                📍 Tavsif: %s
                💵 Narx: %,.0f %s
                📅 Yil: %d
                📦 Miqdor: %d dona
                
                💰 Umumiy narx: %,.0f %s""".formatted(
                product.getName(),
                product.getDescription(),
                product.getPrice(), getCurrencySymbol(product.getPrice()),
                product.getYear(),
                product.getQuantity()
                , totalPrice, getCurrencySymbol(totalPrice)
        );
    }

    public static String renderForCart(Product product,int count ) {
        return """
                🔰 %s
                💵 Narx: %,.0f %s
                📦 Miqdor: %d dona""".formatted(
                product.getName(),
                product.getPrice(), getCurrencySymbol(product.getPrice()),
                count);
    }

    private static String getCurrencySymbol(double price) {
        return "USD";
    }
}

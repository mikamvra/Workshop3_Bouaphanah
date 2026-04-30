package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public List<Product> cart = new ArrayList<>();

    public void add(Product product) {
        cart.add(product);
    }

    public void remove(String id) {
        cart.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    public void clear() {
        cart.clear();
    }

    public List<Product> getCart() {
        return cart;
    }


    public double getTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }
}

package org.example;

import org.example.Amazon.Amazon;
import org.example.Amazon.Cost.*;
import org.example.Amazon.Item;
import org.example.Amazon.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AmazonIntegrationTest {

    static class MemoryCart implements ShoppingCart {
        private final List<Item> items = new ArrayList<>();

        @Override
        public void add(Item item) { items.add(item); }

        @Override
        public List<Item> getItems() { return items; }

        @Override
        public int numberOfItems() { return items.size(); }
    }

    private MemoryCart cart;

    @BeforeEach
    void setup() {
        cart = new MemoryCart();
    }

    @Test
    @DisplayName("specification-based - no items = total 0")
    void noItemsReturnsZero() {
        Amazon amazon = new Amazon(cart, List.of(new RegularCost(), new DeliveryPrice(), new ExtraCostForElectronics()));
        assertThat(amazon.calculate()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("structural-based - calculates with multiple price rules")
    void calculatesTotalWithAllRules() {
        cart.add(new Item(ItemType.OTHER, "Book", 2, 10.0));
        cart.add(new Item(ItemType.ELECTRONIC, "Laptop", 1, 500.0));

        Amazon amazon = new Amazon(cart, List.of(new RegularCost(), new DeliveryPrice(), new ExtraCostForElectronics()));

        double expected = (2 * 10.0 + 1 * 500.0) + 5.0 + 7.5; // regular + delivery + electronics fee
        assertThat(amazon.calculate()).isEqualTo(expected);
    }
}





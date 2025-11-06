package org.example;

import org.example.Amazon.Amazon;
import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.example.Amazon.Item;
import org.example.Amazon.ShoppingCart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AmazonUnitTest {

    @Test
    @DisplayName("specification-based - returns zero when no rules")
    void returnsZeroWhenNoRules() {
        ShoppingCart cart = mock(ShoppingCart.class);
        when(cart.getItems()).thenReturn(List.of());

        Amazon amazon = new Amazon(cart, List.of());
        double result = amazon.calculate();

        assertThat(result).isEqualTo(0.0);
        // Removed verify(cart) line — not needed
    }

    @Test
    @DisplayName("structural-based - sums prices from all rules")
    void sumsPricesFromAllRules() {
        ShoppingCart cart = mock(ShoppingCart.class);
        List<Item> fakeItems = List.of(new Item(ItemType.OTHER, "Book", 1, 10.0));
        when(cart.getItems()).thenReturn(fakeItems);

        PriceRule rule1 = mock(PriceRule.class);
        PriceRule rule2 = mock(PriceRule.class);

        when(rule1.priceToAggregate(fakeItems)).thenReturn(20.0);
        when(rule2.priceToAggregate(fakeItems)).thenReturn(5.5);

        Amazon amazon = new Amazon(cart, List.of(rule1, rule2));
        double total = amazon.calculate();

        assertThat(total).isEqualTo(25.5);
        verify(rule1).priceToAggregate(fakeItems);
        verify(rule2).priceToAggregate(fakeItems);
    }
}
package org.example;

import org.example.Barnes.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BarnesAndNobleTest {

    @Test
    @DisplayName("specification-based")
    void returnsNullWhenOrderIsNull() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BarnesAndNoble bn = new BarnesAndNoble(db, process);

        assertThat(bn.getPriceForCart(null)).isNull();
        verifyNoInteractions(db, process);
    }

    @Test
    @DisplayName("specification-based")
    void totalsPriceForSingleKnownIsbn() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BarnesAndNoble bn = new BarnesAndNoble(db, process);

        // Given: the database knows this ISBN
        when(db.findByISBN("111")).thenReturn(new Book("111", /*price*/20, /*qty*/100));

        Map<String,Integer> order = new HashMap<>();
        order.put("111", 3);

        PurchaseSummary summary = bn.getPriceForCart(order);

        assertThat(summary.getTotalPrice()).isEqualTo(3 * 20);
        // verify we attempted to buy 3 copies of this Book
        verify(process, times(1)).buyBook(new Book("111", 20, 100), 3);
    }

    @Test
    @DisplayName("structural-based")
    void multipliesAcrossMultipleIsbnsAndCallsProcessForEach() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BarnesAndNoble bn = new BarnesAndNoble(db, process);

        when(db.findByISBN("A")).thenReturn(new Book("A", 10, 5));
        when(db.findByISBN("B")).thenReturn(new Book("B", 25, 10));

        Map<String,Integer> order = new HashMap<>();
        order.put("A", 2);
        order.put("B", 1);

        PurchaseSummary summary = bn.getPriceForCart(order);

        assertThat(summary.getTotalPrice()).isEqualTo(2*10 + 1*25);
        verify(process).buyBook(new Book("A", 10, 5), 2);
        verify(process).buyBook(new Book("B", 25, 10), 1);
        verifyNoMoreInteractions(process);
    }
}
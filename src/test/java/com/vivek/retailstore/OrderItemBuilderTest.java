package com.vivek.retailstore;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vivek.retailstore.domain.checkout.OrderItem;
import com.vivek.retailstore.domain.checkout.OrderItemBuilder;
import com.vivek.retailstore.domain.cost.Cost;
import com.vivek.retailstore.domain.cost.SalesTaxCalculator;
import com.vivek.retailstore.domain.shoppingcart.CartItem;
import com.vivek.retailstore.testdata.TestProduct;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemBuilderTest {

    @Autowired
    private SalesTaxCalculator salesTaxCalculator;

    @Test
    public void testPurchaseOrderBuilder(){
        BigDecimal quantity = new BigDecimal(1);
        CartItem iphoneItem = new CartItem(TestProduct.phone, quantity);


        OrderItemBuilder orderItemBuilder = new OrderItemBuilder();
        OrderItem orderItem = orderItemBuilder.cartItem(iphoneItem).salesTaxCalculator(salesTaxCalculator).build();

        assertNotNull(orderItem);
        assertEquals(iphoneItem.getProduct().getProductName(), orderItem.getProductName());
        assertEquals(iphoneItem.getProduct().getUnitPrice(), orderItem.getUnitPrice());

        Cost totalCost = orderItem.getTotalCost();
        assertEquals(iphoneItem.getProduct().getUnitPrice().multiply(quantity), totalCost.getGrossAmount());

    }
}

package com.vivek.retailstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.vivek.retailstore.domain.productcatalog.Product;
import com.vivek.retailstore.domain.productcatalog.ProductRepository;
import com.vivek.retailstore.domain.scanning.Scanner;
import com.vivek.retailstore.domain.shoppingcounter.ShoppingCounter;
import com.vivek.retailstore.domain.shoppingcounter.ShoppingCounterAggregate;
import com.vivek.retailstore.testdata.TestProduct;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ShoppingCounterIntegrationTest {

    @Autowired
    private ShoppingCounterAggregate.ShoppingCounterAggregateFactory shoppingCounterAggregateFactory;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setup(){
        productRepository.save(TestProduct.phone);
    }

    @Test
    public void testScanValidProduct(){

        ShoppingCounterAggregate shoppingCounterAggregate = shoppingCounterAggregateFactory.getShoppingCounterAggregate(new ShoppingCounter("my counter", new Scanner(
                "LX500")));

        Optional<Product> product = shoppingCounterAggregate.scanProduct(TestProduct.phone.getBarCode());
        assertTrue(product.isPresent());

        assertEquals(product.get().getProductName(), TestProduct.phone.getProductName());
    }

    @Test
    public void testScanInvValidProduct(){

        ShoppingCounterAggregate shoppingCounterAggregate = shoppingCounterAggregateFactory.getShoppingCounterAggregate(new ShoppingCounter("my counter", new Scanner(
                "LX500")));

        Optional<Product> product = shoppingCounterAggregate.scanProduct("32434343434");
        assertFalse(product.isPresent());

    }



}

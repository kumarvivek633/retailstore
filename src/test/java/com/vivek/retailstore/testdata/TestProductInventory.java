package com.vivek.retailstore.testdata;

import java.math.BigDecimal;

import com.vivek.retailstore.domain.cost.SalesTaxCategory;
import com.vivek.retailstore.domain.inventorymanagement.ProductInventory;
import com.vivek.retailstore.domain.productcatalog.Product;

public class TestProductInventory {

    public static final ProductInventory iphoneInventory = new ProductInventory(TestProduct.phone,
            new BigDecimal(10));

    public static final ProductInventory iphoneCaseInventory = new ProductInventory(TestProduct.phoneCase,
            new BigDecimal(10));
}

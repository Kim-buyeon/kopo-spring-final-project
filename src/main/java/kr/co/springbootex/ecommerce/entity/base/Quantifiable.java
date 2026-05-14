package kr.co.springbootex.ecommerce.entity.base;

import kr.co.springbootex.ecommerce.entity.Product;

public interface Quantifiable {
    int getQuantity();
    Product getProduct();
}

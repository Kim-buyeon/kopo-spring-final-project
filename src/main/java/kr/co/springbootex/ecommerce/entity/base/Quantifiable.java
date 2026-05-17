package kr.co.springbootex.ecommerce.entity.base;

import kr.co.springbootex.ecommerce.entity.Product;

public interface Quantifiable<ID> {
    ID getId();
    int getQuantity();
    int getPrice();
    Product getProduct();
}

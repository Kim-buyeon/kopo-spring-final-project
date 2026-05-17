package kr.co.springbootex.ecommerce.entity.base;

public interface Nameable<ID> {
    String getName();
    ID getId();
}

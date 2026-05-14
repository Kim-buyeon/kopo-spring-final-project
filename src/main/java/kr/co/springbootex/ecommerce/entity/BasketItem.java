package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.base.Quantifiable;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketItem implements Quantifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nb_basket", scale = 9)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nb_basket")
    private Basket basket;

    @Column(name = "cn_basket_item_order", scale = 9, nullable = false)
    private int itemTurn;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_product")
    private Product product;

    @Column(name = "qt_basket_item_price", scale = 9)
    private int price;

    @Column(name = "qt_basket_item", scale = 9)
    private int quantity;

    @Column(name = "qt_basket_item_amount", scale = 9)
    private int amount;
}

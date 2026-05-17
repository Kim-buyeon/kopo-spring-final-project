package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.springbootex.ecommerce.entity.base.Quantifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ORDER_ITEM")
public class OrderItem implements Quantifiable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_order_item", length = 60)
    private String id;

    @Column(name = "cn_order_item", scale = 5, nullable = false)
    private int orderTurn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_product")
    private Product product;

    @Column(name = "qt_unit_price", scale = 9, nullable = false)
    private int price;

    @Column(name = "qt_order_item", scale = 9, nullable = false)
    private int quantity;


}

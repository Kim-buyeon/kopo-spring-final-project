package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.base.Quantifiable;
import lombok.*;
import org.apache.ibatis.annotations.One;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ORDER_ITEM")
public class OrderItem implements Quantifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_order_item", length = 50)
    private String id;

    @Column(name = "cn_order_item", scale = 5, nullable = false)
    private int orderTurn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Orders order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_product")
    private Product product;

    @Column(name = "qt_unit_price", scale = 9, nullable = false)
    private int price;

    @Column(name = "qt_order_item", scale = 9, nullable = false)
    private int quantity;


}

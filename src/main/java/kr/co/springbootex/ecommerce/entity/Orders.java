package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.base.Quantifiable;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ORDER")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_order", length = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "qt_order_amount", scale = 9)
    private int amount;

    @Column(name = "nm_order_person", length = 100)
    private String ordererName;

    @Column(name = "nm_delivery_address", length = 200)
    private String address;

    @Column(name = "da_order")
    private LocalDate orderDate;

    @Column(name = "st_order", length = 4)
    @ColumnDefault("10")
    private String status;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "no_product")
//    private Product product;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderItem orderItem;


}

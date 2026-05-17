package kr.co.springbootex.ecommerce.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "id_order", length = 60)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


}

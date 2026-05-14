package kr.co.springbootex.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.base.Nameable;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_PRODUCT")
public class Product implements Nameable {

    //dto 수정
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "no_product", length = 30)
    private String id;

    @Column(name = "nm_product", nullable = false, unique = true)
    private String name;

    @Lob
    @Column(name = "nm_detail_explain", length = 4000)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
    @Column(name = "dt_start_date", length = 8)
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
    @Column(name = "dt_end_date", length = 8)
    private LocalDate endDate;

    @Column(name = "qt_sale_price", precision = 9, nullable = false)
    private int price;

    @Column(name = "qt_stock", precision = 9)
    private int stock;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BasketItem> basketItems = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Content content;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}

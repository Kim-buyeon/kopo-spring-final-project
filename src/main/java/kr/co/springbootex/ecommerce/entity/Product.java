package kr.co.springbootex.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.base.Nameable;
import kr.co.springbootex.ecommerce.entity.constant.ProductStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUCT")
public class Product implements Nameable<String> {

    //dto 수정
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "no_product", length = 60)
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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "pr_status",length = 20)
    private ProductStatus productStatus;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BasketItem> basketItems = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Content content;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    
    public void setContent(Content content) {
    	this.content = content;
    	if(content != null) {
    		content.setProduct(this);
    	}
    }
    
    public void addStock(int stock) {
    	this.stock += stock;
    }
    
    public void substractStock(int stock) {
    	this.stock -= stock;
    	if(this.stock <=0) {
    		setStock(0);
    		setProductStatus(ProductStatus.SOLD_OUT);
    	}
    }
    
    

}

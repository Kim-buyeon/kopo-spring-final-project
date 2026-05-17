package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.key.CategoryProductId;
import lombok.*;

@Entity
@Table(name = "TB_CATEGORY_PRODUCT_MAPPING")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductMapping {

    @EmbeddedId
    private CategoryProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "nm_category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "no_product")
    private Product product;

    @Column(name = "cn_order", scale = 4)
    private int cTurn;
}

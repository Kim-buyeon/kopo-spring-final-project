package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.base.Nameable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category implements Nameable<Long> {

    @Id
    @Column(name = "nb_category")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nm_category", nullable = false)
    private String name;

    @Column(name = "cn_order")
    private String sortOrder;
    
    @Column(name = "cn_level", scale = 4)
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @OrderBy("sortOrder ASC")
    private List<Category> children = new ArrayList<>();
}

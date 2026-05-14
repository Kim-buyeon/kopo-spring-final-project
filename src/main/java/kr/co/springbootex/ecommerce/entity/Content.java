package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import kr.co.springbootex.ecommerce.entity.base.Nameable;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CONTENT")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Content implements Nameable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nb_file", scale = 19)
    private Long id;

    @Column(name = "nm_org_file", length = 200)
    private String name;

    @Column(name = "nm_save_file", length = 200)
    private String sFileName;

    @Column(name = "nm_file_path", length = 200)
    private String filePath;

    @Column(name = "nm_content_type", length = 20)
    private String contentType;

    @Column(name = "qt_file_size", scale = 19)
    private int size;

    @Column(name = "nm_file_ext", length = 10, nullable = false)
    private String extension;

    @Column(name = "da_create_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}

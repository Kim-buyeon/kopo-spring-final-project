package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import kr.co.springbootex.ecommerce.entity.constant.UserClassification;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "영어 대소문자, 숫자로 이루어져 합니다.")
    @Column(name = "userId", nullable = false, unique = true)
    private String userId;

    @Column(name = "password", nullable = false, unique = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)[a-zA-Z\\\\d]{5,15}$",
            message = "영문 대문자, 소문자, 숫자를 각각 최소 1개 이상 포함해야 하며, 영문자와 숫자로만 이루어져야 합니다.")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "telNo")
    private String telNo;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "userClassification", nullable = false)
    private UserClassification userClassification;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CartItem> cartitems = new ArrayList<>();
}

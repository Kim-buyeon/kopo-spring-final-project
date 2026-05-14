package kr.co.springbootex.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import kr.co.springbootex.ecommerce.entity.base.Nameable;
import kr.co.springbootex.ecommerce.entity.constant.UserClassification;
import kr.co.springbootex.ecommerce.entity.constant.UserStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class User implements Nameable {

    //dto도 수정
    @Id
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "영어 대소문자, 숫자로 이루어져 합니다.")
    @Column(name = "id_user", length = 100)
    private String userId;

    @Column(name = "nm_user", nullable = false, length = 100)
    private String name;

    @Column(name = "nm_passwd", nullable = false, length = 256)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)[a-zA-Z\\\\d]{5,15}$",
            message = "영문 대문자, 소문자, 숫자를 각각 최소 1개 이상 포함해야 하며, 영문자와 숫자로만 이루어져야 합니다.")
    private String password;

    @Column(name = "no_mobile", nullable = false, length = 30)
    private String telNo;

    @Email
    @Column(name = "nm_email", nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_status", nullable = false, length = 4)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "cd_user_type", nullable = false, length = 4)
    private UserClassification userClassification;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Basket basket;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<BasketItem> cartitems = new ArrayList<>();
}

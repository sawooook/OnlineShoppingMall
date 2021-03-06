package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String phone;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    private List<BillingInfo> billingInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Product> products = new ArrayList<>();

    @Embedded
    private Address address;

    // 처음 insert 시 userStatus 업데이트를 위한 코드
    @PrePersist
    public void prePersist() {
        if (this.getUserStatus() == null) {
            this.userStatus = UserStatus.SIGN;
        }
    }

    public User changeEncodingPassword(String encodingPassword) {
        return User.builder().password(encodingPassword)
                .id(this.id).email(this.email)
                .phone(this.phone).address(this.address)
                .name(this.name).userRole(this.userRole).userStatus(this.userStatus).build();

    }

    public boolean isNotDeleteUser() {
        return getUserStatus() == UserStatus.SIGN;
    }

//
//        if (user.getUserStatus() == UserStatus.SIGN) {
//            if (passwordEncoder.matches(passWord, user.getPassword())){
//                UserLoginResponseDto userResponseDto = UserLoginResponseDto.builder()
//                        .id(user.getId()).email(user.getEmail()).phone(user.getPhone()).name(user.getName()).address(user.getAddress()).build();
//                return Optional.ofNullable(userResponseDto);
//            }
//
//        }
}

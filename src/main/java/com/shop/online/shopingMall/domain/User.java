package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import com.shop.online.shopingMall.dto.user.UserDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @Embedded
    private Address address;

    private String pushToken;


    // 회원가입시 사용하는 생성자
    public User(UserDto userDto, String encodePassWord) {
        this.name = userDto.getName();
        this.phone = userDto.getPhone();
        this.email = userDto.getEmail();
        this.phone = userDto.getPhone();
        this.password = encodePassWord;
        this.userRole = UserRole.customer;
        this.userStatus = UserStatus.SIGN;
        this.address = new Address(userDto.getAddress().getAddressCode(), userDto.getAddress().getAddressDetail());
    }

    public boolean isNotDeleteUser() {
        return getUserStatus() == UserStatus.SIGN;
    }

    /**
    * 유저가 활성화된 카드를 갖고있는지 체크한다.
    * */
    public Optional<BillingInfo> activeBillingInfo() {
        return billingInfoList.stream()
                .filter(billingInfo -> billingInfo.isActiveBillingInfo())
                .findAny();
    }

    /**
    * 유저 정보를 삭제 상태로 변경하고 등록된
    * 카드 정보를 모두 삭제한다.
    * */
    public void delete() {
        userStatus = UserStatus.DELETE;
        getBillingInfoList().forEach(BillingInfo::delete);
    }
}

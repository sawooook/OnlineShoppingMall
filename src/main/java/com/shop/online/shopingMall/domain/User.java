package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import com.shop.online.shopingMall.dto.user.UserDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Optional<BillingInfo> activeBillingInfo() {
        BillingInfo findBillingInfo = null;

        for (BillingInfo billingInfo : billingInfoList) {
            if (billingInfo.getBillingInfoStatus() == BillingInfoStatus.ACTIVE) {
                findBillingInfo = billingInfo;
                break;
            }
        }
        return Optional.ofNullable(findBillingInfo);
    }

    public Optional<BillingInfo> hasActiveBillingInfo(CardName cardName) {
        List<BillingInfo> cardList = this.getBillingInfoList();
        BillingInfo billingInfo = null;

        for (BillingInfo card : cardList) {
            billingInfo = card.isActiveBillingInfo(cardName).orElseThrow(NotFoundBillingInfoException::new);
        }
        return Optional.ofNullable(billingInfo);
    }

    /*
    * 유저 정보를 삭제 상태로 변경하고 등록된
    * 카드 정보를 모두 삭제한다.
    * */
    public void delete() {
        userStatus = UserStatus.DELETE;
        for (BillingInfo billingInfo : getBillingInfoList()) {
            billingInfo.delete();
        }
    }
}

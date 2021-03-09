package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity implements UserDetails {

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

    /*
    * Spring Security 관련 메소드
    * */

    // 계정이 갖고 있는 권한 목록을 리턴함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // 계정의 이름을 리턴함
    @Override
    public String getUsername() {
        return null;
    }

    //계정이 만료되었는지 체크한다
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    //계정이 잠겨있는지 체크한다.
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    //계정 비밀번호가 만료되지 않았는지 체크한다.
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    // 계정이 활성화 상태인지 체크한다.
    @Override
    public boolean isEnabled() {
        return false;
    }
}

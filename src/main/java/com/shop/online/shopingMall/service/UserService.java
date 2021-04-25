package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.config.JwtTokenProvider;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import com.shop.online.shopingMall.dto.user.AddressDto;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.user.UserDto;
import com.shop.online.shopingMall.dto.user.UserLoginResponseDto;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
    * 로그인 체크
    * 1) 입력한 이메일과 비밀번호가 일치하는지 테스트함
    * 2) 회원탈퇴한 아이디일 경우 로그인을 하지못하도록 현재 가입된 유저만 로그인하도록함
    * */
    @Transactional
    public UserLoginResponseDto loginCheck(String email, String passWord) throws NotFoundUserException {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

        boolean validUser = isValidUser(user.isNotDeleteUser(), isValidPassword(passWord, user.getPassword()));

        if (validUser) {
            String token = jwtTokenProvider.createToken(user.getId());
            return new UserLoginResponseDto(user, token, new AddressDto(user));
        } else {
            throw new NotFoundUserException();
        }
    }

    /**
    * 해당 비밀번호를 받아와서 password_Encoder를 이용해서 인코딩 한 후, 유저를 저장한다.
    * */

    @Transactional
    public User save(@NonNull UserDto userDto) {
        String encodePassWord = passwordEncoder.encode(userDto.getPassWord());
        return userRepository.save(new User(userDto, encodePassWord));
    }

    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
    * 유저 탈퇴시 사용
    * 1) 만약 유저가 현재 배송중인 물품이 있을 경우
    * 삭제를 못하도록함
    * 2) 배송중인 물품이 없을 경우 유저 상태를 DELETE로 변경하고, 카드 등록키와 카드 상태도 DELETE로 변경
    * */
    @Transactional(rollbackFor = Exception.class)
    public void unRegister(Long id) throws NotFoundUserException {
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        isDeletableUser(user);
    }

    /**
    * 아이디로 유저를 찾을때 사용
    * */
    public User findUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        return user;
    }

    private void isDeletableUser(User user) {
        for (Order order : user.getOrderList()) {
            if (isOrderReadyPresent(order)) {
                return;
            }
        }
        user.delete();
    }

    private boolean isOrderReadyPresent(Order order) {
        return order.getOrderStatus() == OrderStatus.ready;
    }

    private boolean isValidPassword(String passWord, String encodingPassWord) {
        return passwordEncoder.matches(passWord, encodingPassWord);
    }

    private boolean isValidUser(Boolean userStatus, Boolean passWordCorrectStatus) {
        return userStatus && passWordCorrectStatus;
    }
}

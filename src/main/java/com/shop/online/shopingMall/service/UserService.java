package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    * 해당 비밀번호를 받아와서 password_Encoder를 이용해서 인코딩 한 후, 유저를 저장한다.
    * */
    public Long save(User user) {
        String encodePassWord = passwordEncoder.encode(user.getPassword());
        User encodingUser = user.changeEncodingPassword(encodePassWord);
        return userRepository.save(encodingUser);
    }

}

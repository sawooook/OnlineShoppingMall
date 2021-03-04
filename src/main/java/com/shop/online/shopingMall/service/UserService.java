package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import com.shop.online.shopingMall.dto.UserLoginResponseDto;
import com.shop.online.shopingMall.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.NotActiveException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserLoginResponseDto> loginCheck(String email, String passWord) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        if (user.getUserStatus() == UserStatus.SIGN) {
            if (passwordEncoder.matches(passWord, user.getPassword())){
                UserLoginResponseDto userResponseDto = UserLoginResponseDto.builder()
                        .id(user.getId()).email(user.getEmail()).phone(user.getPhone()).name(user.getName()).address(user.getAddress()).build();
                return Optional.ofNullable(userResponseDto);
            }
        }
        return null;
    }

    /*
    * 해당 비밀번호를 받아와서 password_Encoder를 이용해서 인코딩 한 후, 유저를 저장한다.
    * */
    public Long save(User user) {
        String encodePassWord = passwordEncoder.encode(user.getPassword());
        User encodingUser = user.changeEncodingPassword(encodePassWord);
        return userRepository.save(encodingUser).getId();
    }

    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void unRegister(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("아아디 가 존재하지 않습니다."));
        userRepository.delete(user);
    }

}

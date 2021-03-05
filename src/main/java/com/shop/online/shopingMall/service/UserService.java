package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import com.shop.online.shopingMall.dto.user.UserLoginResponseDto;
import com.shop.online.shopingMall.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /*
    * 로그인 체크
    * 1) 입력한 이메일과 비밀번호가 일치하는지 테스트함
    * 2) 회원탈퇴한 아이디일 경우 로그인을 하지못하도록 현재 가입된 유저만 로그인하도록함
    * */
    public Optional<UserLoginResponseDto> loginCheck(String email, String passWord) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        if (user.getUserStatus() == UserStatus.SIGN) {
            if (passwordEncoder.matches(passWord, user.getPassword())){
                UserLoginResponseDto userResponseDto = UserLoginResponseDto.builder()
                        .id(user.getId()).email(user.getEmail()).phone(user.getPhone()).name(user.getName()).address(user.getAddress()).build();
                return Optional.ofNullable(userResponseDto);
            }
        }
        return Optional.empty();
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

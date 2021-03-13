package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.config.JwtTokenProvider;
import com.shop.online.shopingMall.dto.user.UserMypageResponse;
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
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    /*
    * 로그인 체크
    * 1) 입력한 이메일과 비밀번호가 일치하는지 테스트함
    * 2) 회원탈퇴한 아이디일 경우 로그인을 하지못하도록 현재 가입된 유저만 로그인하도록함
    * */
    public UserLoginResponseDto loginCheck(String email, String passWord) throws NotFoundUserException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundUserException("유저를 찾을 수 없습니다."));

        boolean validUser = isValidUser(user.isNotDeleteUser(), isValidPassword(passWord, user.getPassword()));

        if (validUser) {
            String token = jwtTokenProvider.createToken(user.getId());
            UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.toDto(user);
            userLoginResponseDto.updateToken(token);
            return userLoginResponseDto;
        } else {
            throw new NotFoundUserException("로그인 실패");
        }
    }

    private boolean isValidPassword(String passWord, String encodingPassWord) {
        return passwordEncoder.matches(passWord, encodingPassWord);
    }

    private boolean isValidUser(Boolean userStatus, Boolean passWordCorrectStatus) {
        return userStatus && passWordCorrectStatus;
    }

    /*
    * 해당 비밀번호를 받아와서 password_Encoder를 이용해서 인코딩 한 후, 유저를 저장한다.
    * */

    public Long save(@NonNull UserDto userDto) {
        User user = userDto.toEntity();
        String encodePassWord = passwordEncoder.encode(user.getPassword());
        User encodingUser = user.changeEncodingPassword(encodePassWord);
        return userRepository.save(encodingUser).getId();
    }

    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void unRegister(Long id) throws NotFoundUserException {
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        userRepository.delete(user);
    }

    public UserMypageResponse findUser(Long id) throws NotFoundUserException{
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        return UserMypageResponse.toDto(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }
}

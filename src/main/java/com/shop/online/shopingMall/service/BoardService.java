package com.shop.online.shopingMall.service;

import com.google.firebase.messaging.Message;
import com.shop.online.shopingMall.domain.Board;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.BoardType;
import com.shop.online.shopingMall.dto.board.BoardRequestDto;
import com.shop.online.shopingMall.dto.fcm.FcmRequestDto;
import com.shop.online.shopingMall.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final SecurityService securityService;
    private final UserService userService;

    public void save(BoardRequestDto boardRequestDto) {
        Long userId = securityService.findUserIdbyToken();
        User user = userService.findById(userId);

        Board board = BoardRequestDto.toEntity(boardRequestDto, user);
        boardRepository.save(board);

        FcmRequestDto fcmRequestDto = FcmRequestDto.toEntity(board);

        if (board.isSendPush() && (board.getBoardType() == BoardType.FAQ)) {
//            pushService.checkSendUser(fcmRequestDto);
        }
    }
}

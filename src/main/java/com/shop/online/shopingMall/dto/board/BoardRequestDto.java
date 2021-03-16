package com.shop.online.shopingMall.dto.board;

import com.shop.online.shopingMall.domain.Board;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.BoardType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
    private BoardType type;
    private boolean isSendPush;

    public static Board toEntity(BoardRequestDto boardRequestDto, User user) {
        return Board.builder()
                .content(boardRequestDto.getContent())
                .title(boardRequestDto.getTitle())
                .boardType(boardRequestDto.getType())
                .user(user)
                .isSendPush(boardRequestDto.isSendPush())
                .build();
    }
}

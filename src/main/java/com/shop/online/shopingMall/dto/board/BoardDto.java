package com.shop.online.shopingMall.dto.board;

import com.shop.online.shopingMall.domain.Board;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.BoardType;
import lombok.*;

@Data
@NoArgsConstructor
public class BoardDto {
    private String title;
    private String content;
    private BoardType type;
    private boolean isSendPush;

    public BoardDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.type = board.getBoardType();
        this.isSendPush = board.isSendPush();
    }
}

package com.shop.online.shopingMall.dto.fcm;

import com.shop.online.shopingMall.domain.Board;
import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FcmRequestDto {
    private String title;
    private  String content;

    public static FcmRequestDto toEntity(Board board) {
        return FcmRequestDto.builder().content(board.getContent()).title(board.getTitle()).build();
    }
}

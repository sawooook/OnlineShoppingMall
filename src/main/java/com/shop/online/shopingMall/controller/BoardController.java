package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.dto.board.BoardRequestDto;
import com.shop.online.shopingMall.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.ApiResponse;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    public ApiResponse<String> addBoard(@RequestBody BoardRequestDto boardRequestDto) {
        boardService.save(boardRequestDto);
        return ApiResponse.success("동록 성공");
    }
}

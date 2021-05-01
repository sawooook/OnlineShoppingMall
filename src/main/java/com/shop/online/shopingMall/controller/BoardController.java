package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.dto.board.BoardDto;
import com.shop.online.shopingMall.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shop.online.shopingMall.util.ApiResponse;

import static com.shop.online.shopingMall.util.ApiResponse.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
    * 게시글 등록 API
    * */
    @PostMapping
    public ApiResponse<BoardDto> addBoard(@RequestBody BoardDto boardRequestDto) {
        BoardDto response = boardService.save(boardRequestDto);
        return success(response);
    }
}

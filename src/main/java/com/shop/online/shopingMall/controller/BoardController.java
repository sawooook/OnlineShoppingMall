package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.dto.board.BoardRequestDto;
import com.shop.online.shopingMall.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    public ResponseEntity addBoard(@RequestBody BoardRequestDto boardRequestDto) {
        boardService.save(boardRequestDto);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, "등록 성공", null));
    }
}

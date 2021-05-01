package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Board;
import com.shop.online.shopingMall.dto.board.BoardDto;
import com.shop.online.shopingMall.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardDto save(BoardDto boardRequestDto) {
        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.isSendPush(), boardRequestDto.getType());
        boardRepository.save(board);
        return new BoardDto(board);
    }
}

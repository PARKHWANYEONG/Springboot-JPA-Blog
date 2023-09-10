package com.welcome.blog.service;

import com.welcome.blog.Repository.BoardRepository;
import com.welcome.blog.domain.Board;
import com.welcome.blog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(Board board, User user) {
        board.setUser(user);
        board.setCount(0);
        boardRepository.save(board);
    }
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));
    }
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    public void boardModify(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("boardModify: 아이디를 찾을 수 없습니다."));
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }
}

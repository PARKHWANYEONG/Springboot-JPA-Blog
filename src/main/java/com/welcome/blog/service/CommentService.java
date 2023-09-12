package com.welcome.blog.service;


import com.welcome.blog.Repository.BoardRepository;
import com.welcome.blog.Repository.CommentRepository;
import com.welcome.blog.Repository.UserRepository;
import com.welcome.blog.config.auth.PrincipalDetails;
import com.welcome.blog.domain.Board;
import com.welcome.blog.domain.Comment;
import com.welcome.blog.domain.User;
import com.welcome.blog.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    public void save(CommentDto commentDto) {
        User user = userRepository.findById(commentDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("회원ID를 찾을수 없습니다."));
        Board board = boardRepository.findById(commentDto.getBoardId()).orElseThrow(() -> new IllegalArgumentException("게시글 ID를 찾을수 없습니다."));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setUser(user);
        comment.setBoard(board);
        board.getComments().add(comment);
        commentRepository.save(comment);

    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

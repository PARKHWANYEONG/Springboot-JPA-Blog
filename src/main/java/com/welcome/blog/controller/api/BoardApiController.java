package com.welcome.blog.controller.api;


import com.welcome.blog.config.auth.PrincipalDetails;
import com.welcome.blog.domain.Board;
import com.welcome.blog.domain.Comment;
import com.welcome.blog.dto.CommentDto;
import com.welcome.blog.dto.ResponseDto;
import com.welcome.blog.service.BoardService;
import com.welcome.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> Save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
        boardService.save(board, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board) {
        boardService.boardModify(id,board);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{boardId}/comment")
    public ResponseDto<Integer> commentSave(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/comment/{commentId}")
    public ResponseDto<Integer> commentDelete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
}

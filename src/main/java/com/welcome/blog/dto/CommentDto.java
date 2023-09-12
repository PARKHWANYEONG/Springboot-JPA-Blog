package com.welcome.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long boardId;
    private Long userId;
    private String content;
}

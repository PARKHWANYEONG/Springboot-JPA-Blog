package com.welcome.blog.controller.api;


import com.welcome.blog.domain.RoleType;
import com.welcome.blog.domain.User;
import com.welcome.blog.dto.ResponseDto;
import com.welcome.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> join(@RequestBody User user) {
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}

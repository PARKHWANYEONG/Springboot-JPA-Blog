package com.welcome.blog.controller.api;


import com.welcome.blog.config.auth.PrincipalDetails;
import com.welcome.blog.config.auth.PrincipalDetailsService;
import com.welcome.blog.domain.Comment;
import com.welcome.blog.domain.RoleType;
import com.welcome.blog.domain.User;
import com.welcome.blog.dto.ResponseDto;
import com.welcome.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> join(@RequestBody User user) {
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.userModify(user, principalDetails);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

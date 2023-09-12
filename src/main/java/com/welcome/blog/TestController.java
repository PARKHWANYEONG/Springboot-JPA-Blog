package com.welcome.blog;

import com.welcome.blog.Repository.BoardRepository;
import com.welcome.blog.Repository.UserRepository;
import com.welcome.blog.domain.Board;
import com.welcome.blog.domain.RoleType;
import com.welcome.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;


@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping("/join")
    public String join(User user) {
        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getId() = " + user.getPassword());
        System.out.println("user.getId() = " + user.getEmail());
        System.out.println("user.getId() = " + user.getRoleType());
        System.out.println("user.getId() = " + user.getCreatedDate());
        System.out.println("user.getId() = " + user.getLastModifiedDate());
        user.setRoleType(RoleType.USER);
        userRepository.save(user);
        return "가입완료";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> select(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자는 없습니다. id=" + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> selectAll() {
        List<User> all = userRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/users/page")
    public Page<User> page(@PageableDefault(size = 2, page = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page;
    }

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardRepository.findById(id).get();
    }
}

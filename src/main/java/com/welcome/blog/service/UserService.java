package com.welcome.blog.service;


import com.welcome.blog.Repository.UserRepository;
import com.welcome.blog.config.auth.PrincipalDetails;
import com.welcome.blog.domain.RoleType;
import com.welcome.blog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    public void join(User user) {
        String encPassword = encoder.encode(user.getPassword()); //비밀번호 해쉬화
        user.setRoleType(RoleType.USER);
        user.setPassword(encPassword);
        userRepository.save(user);
    }
    public void userModify(User updateUser, PrincipalDetails principalDetails) {
        User user = userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("userModify: 회원 찾기 실패"));
        user.setEmail(updateUser.getEmail());
        String encPassword = encoder.encode(updateUser.getPassword());
        user.setPassword(encPassword);
        principalDetails.setUser(user);
    }
}

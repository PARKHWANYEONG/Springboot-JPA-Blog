package com.welcome.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.welcome.blog.domain.KakaoProfile;
import com.welcome.blog.domain.OAuthToken;
import com.welcome.blog.domain.User;
import com.welcome.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;



@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    @Value("${password.key}")
    private String pwKey;

    private final UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {

        MultiValueMap<String, String > header = new LinkedMultiValueMap<>();
        header.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        HttpHeaders headers = new HttpHeaders(header);

        String clientId = "6ad49cc974693084d53d706db9a04af3";
        String redirectUrl = "http://localhost:8000/auth/kakao/callback";

        MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUrl);
        params.add("code",code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();
        String requestTokenUrl = "https://kauth.kakao.com/oauth/token";
        ResponseEntity<String> response = rt.exchange(requestTokenUrl, HttpMethod.POST, kakaoTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
             oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String requestProfileUrl = "https://kapi.kakao.com/v2/user/me";

        MultiValueMap<String ,String > requestProfileHeader = new LinkedMultiValueMap<>();
        requestProfileHeader.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        requestProfileHeader.add("Authorization","Bearer "+oAuthToken.getAccess_token());
        HttpHeaders requestProfileHeaders = new HttpHeaders(requestProfileHeader);
        HttpEntity<MultiValueMap<String ,String >> kakaoProfileRequest = new HttpEntity<>(requestProfileHeaders);
        ResponseEntity<String> profileResponse = rt.exchange(requestProfileUrl, HttpMethod.POST, kakaoProfileRequest, String.class);

        KakaoProfile kakaoProfile = null;
        try {
             kakaoProfile = objectMapper.readValue(profileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(pwKey)
                .email(kakaoProfile.getKakao_account().getEmail())
                .oauth("kakao")
                .build();

        //기존 회원이 아닐경우 회원가입
        User originUser = userService.findByUsername(kakaoUser.getUsername());
        if (originUser == null) {
            userService.join(kakaoUser);
        }

        //로그인 처리
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),pwKey);
        SecurityContextHolder.getContext().setAuthentication(token);

        return "redirect:/";
    }
}

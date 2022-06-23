package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.dto.request.FindPasswordDto;
import com.hackathon.findtogether.dto.request.FindUsernameDto;
import com.hackathon.findtogether.dto.request.LoginUserDto;
import com.hackathon.findtogether.exception.CustomException;
import com.hackathon.findtogether.exception.ErrorCode;
import com.hackathon.findtogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    //아이디 중복 체크
    private void validateDuplicateUser(User user) {
        Optional<User> findUser = userRepository.findByLoginId(user.getUsername());
        if (!findUser.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }
    }

    //로그인
    public User login(LoginUserDto user) {
        return userRepository.findByLoginId(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .orElse(null);
    }

    //전체 회원 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    //회원 한명 조회
    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }

    //아이디 찾기 -> 이름 전화번호 일치 -> 아이디 공개
    public User findCheckUsername(FindUsernameDto findUsernameDto) {
        return userRepository.findByNameAndPhone(findUsernameDto).orElse(null);
    }

    //비밀번호 찾기 -> 아이디 전화번호 일치 -> 비번 일부 공개
    public User findCheckPassword(FindPasswordDto findPasswordDto) {
        return userRepository.findByUsernameAndPhone(findPasswordDto).orElse(null);
    }
}

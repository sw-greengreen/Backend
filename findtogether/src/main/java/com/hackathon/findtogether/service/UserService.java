package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.Alarm;
import com.hackathon.findtogether.domain.AlarmType;
import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.domain.UserStatus;
import com.hackathon.findtogether.dto.request.CreateAlarmDto;
import com.hackathon.findtogether.dto.request.FindPasswordDto;
import com.hackathon.findtogether.dto.request.FindUsernameDto;
import com.hackathon.findtogether.dto.request.LoginUserDto;
import com.hackathon.findtogether.exception.CustomException;
import com.hackathon.findtogether.exception.ErrorCode;
import com.hackathon.findtogether.repository.AlarmRepository;
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
    private final AlarmRepository alarmRepository;

    //회원가입
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    //아이디 중복 체크
    private void validateDuplicateUser(User user) {
        Optional<User> findUser = userRepository.findByUsername(user.getUsername());
        if (!findUser.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }
    }

    //로그인
    public User login(LoginUserDto user) {
        return userRepository.findByUsername(user.getUsername())
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

    //아이디로 회원 조회
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    //아이디 찾기 -> 이름 전화번호 일치 -> 아이디 공개
    //이름 & 전화번호로 회원 조회
    public User findCheckUsername(FindUsernameDto findUsernameDto) {
        return userRepository.findByNameAndPhone(findUsernameDto).orElse(null);
    }

    //비밀번호 찾기 -> 아이디 전화번호 일치 -> 비번 일부 공개
    //아이디 & 전화번호로 회원 조회
    public User findCheckPassword(FindPasswordDto findPasswordDto) {
        return userRepository.findByUsernameAndPhone(findPasswordDto).orElse(null);
    }

    @Transactional
    public void upgradePointUser(Long userId) {
        User user = userRepository.findOne(userId);
        user.addPoint(); //변경 감지
      
        // 포인트 추가 시 당사자에게 알림 생성
        CreateAlarmDto alarmDto = CreateAlarmDto.builder()
                .username(user.getUsername())
                .alarmType(AlarmType.POINT)
                .alarmStatus(1)
                .message("분실물을 찾아주셨군요! 포인트가 추가되었어요:)")
                .build();

        Alarm alarm = Alarm.createAlarm(alarmDto,user);
        alarmRepository.save(alarm);
    }

    @Transactional
    public void downgradePointUser(Long userId) {
        User user = userRepository.findOne(userId);
        user.substractPoint(); //변경 감지
    }

    @Transactional
    public void updateAchievementDetecter(Long userId) {
        User user = userRepository.findOne(userId);
        user.setAchievement(UserStatus.DETECTER); //변경감지
    }

    @Transactional
    public void updateAchievementLoser(Long userId) {
        User user = userRepository.findOne(userId);
        user.setAchievement(UserStatus.LOSER); //변경감지
    }

    @Transactional
    public void updateAchievementBasic(Long userId) {
        User user = userRepository.findOne(userId);
        user.setAchievement(UserStatus.BASIC); //변경감지
    }
      
    /*
    public void updatePointUser(String username) {
        User user = userRepository.findByUsername(username).get();
        user.addPoint(10); //변경 감지

        // 포인트 추가 시 당사자에게 알림 생성
        CreateAlarmDto alarmDto = CreateAlarmDto.builder()
                .username(username)
                .alarmType(AlarmType.POINT)
                .alarmStatus(1)
                .message("분실물을 찾아주셨군요! 포인트가 추가되었어요:)")
                .build();

        Alarm alarm = Alarm.createAlarm(alarmDto,user);
        alarmRepository.save(alarm);
    }
    */
}

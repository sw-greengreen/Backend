package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.Alarm;
import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.dto.request.CreateAlarmDto;
import com.hackathon.findtogether.dto.request.UpdateAlarmDto;
import com.hackathon.findtogether.repository.AlarmRepository;
import com.hackathon.findtogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    //알람 조회 by id
    @Transactional
    public Alarm findOne(Long id){
        return alarmRepository.findOne(id);
    }

    // 알람 삭제
    @Transactional
    public void removePost(Long alarmId){
        Alarm alarm = alarmRepository.findOne(alarmId);
        alarmRepository.remove(alarm);
    }

    // 알람 생성
    @Transactional
    public Long createAlarm(CreateAlarmDto createAlarmDto) {

        // 사용자 정보 가져오기
        User user = userRepository.findByUsername(createAlarmDto.getUsername()).get();

        // 알람 생성
        Alarm alarm = Alarm.createAlarm(createAlarmDto, user);

        alarmRepository.save(alarm);

        return alarm.getId();
    }

    // 알람 상태 변경
    @Transactional
    public void updateAlarm(Long alarmId, UpdateAlarmDto updateAlarmDto) {
        Alarm alarm = alarmRepository.findOne(alarmId);
        alarm.updateAlarm(updateAlarmDto);
        alarmRepository.save(alarm);
    }
}

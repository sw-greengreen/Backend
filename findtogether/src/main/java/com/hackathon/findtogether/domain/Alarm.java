package com.hackathon.findtogether.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackathon.findtogether.dto.request.CreateAlarmDto;
import com.hackathon.findtogether.dto.request.CreatePostDto;
import com.hackathon.findtogether.dto.request.UpdateAlarmDto;
import com.hackathon.findtogether.dto.request.UpdatePostDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Alarm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user; // 연관관계의 주인

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    private String message;

    private int alarmStatus; // 0(읽음) 1(안읽음)

    public static Alarm createAlarm(CreateAlarmDto createAlarmDto, User user) {
        Alarm alarm;
        alarm = Alarm.builder()
                .user(user)
                .alarmType(createAlarmDto.getAlarmType())
                .alarmStatus(createAlarmDto.getAlarmStatus())
                .message(createAlarmDto.getMessage())
                .build();
        return alarm;
    }

    public void updateAlarm() {
        this.alarmStatus = 0;
    }
}

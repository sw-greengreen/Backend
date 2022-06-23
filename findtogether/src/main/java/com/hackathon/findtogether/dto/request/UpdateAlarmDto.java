package com.hackathon.findtogether.dto.request;

import com.hackathon.findtogether.domain.AlarmType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAlarmDto {

    private AlarmType alarmType;
    private String message;
    private int alarmStatus;
}

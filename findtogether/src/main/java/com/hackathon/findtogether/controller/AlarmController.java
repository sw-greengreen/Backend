package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.domain.Alarm;
import com.hackathon.findtogether.domain.AlarmType;
import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AlarmController {

    private final AlarmService alarmService;

    // 댓글 or 포인트 알림 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/alarm/{id}")
    public Response getPostByComment(@PathVariable Long id, @RequestParam(value="alarmType") AlarmType alarmType) throws Exception{
        List<Alarm> alarms = alarmService.findAlarmsByType(alarmType, id);
        if (alarms == null)
            return new Response(404,true,"알림이 없습니다.", "");
        return new Response(200,true,"found alarm successfully", alarms);
    }
}

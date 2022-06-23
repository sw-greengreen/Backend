package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.Alarm;
import com.hackathon.findtogether.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlarmRepository {

    private final EntityManager em;

    public void save(Alarm alarm) { em.persist(alarm); }

    public Alarm findOne(Long id) { return em.find(Alarm.class,id); }

    public void remove(Alarm alarm){
        em.remove(alarm);
    }

    public List<Alarm> findAlarmByUsername(String username) {
        return em.createQuery("select a from Alarm a join fetch a.user where a.user.username=:username",Alarm.class)
                .setParameter("username",username)
                .getResultList();
    }

    public List<Alarm> findAlarmByAlarmType(String alarmType) {
        return em.createQuery("select a from Alarm a where a.user.username=:username and a.alarmType =:alarmType",Alarm.class)
                .setParameter("alarmType",alarmType)
                .getResultList();
    }

}

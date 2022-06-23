package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.dto.request.FindPasswordDto;
import com.hackathon.findtogether.dto.request.FindUsernameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

//    public Optional<User> findByLoginId(String login_id) {
//        return em.createQuery("select u from User u where u.login_id = :login_id", User.class)
//                .setParameter("login_id", login_id)
//                .getResultStream().findFirst();
//    }

    public Optional<User> findByUsername(String username) {
        return this.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public Optional<User> findByNameAndPhone(FindUsernameDto findUsernameDto) {
        return em.createQuery("select u from User u where u.name = :name and u.phone = :phone", User.class)
                .setParameter("name", findUsernameDto.getName())
                .setParameter("phone", findUsernameDto.getPhone())
                .getResultStream().findFirst();
    }

    public Optional<User> findByUsernameAndPhone(FindPasswordDto findPasswordDto) {
        return em.createQuery("select u from User u where u.username = :username and u.phone = :phone", User.class)
                .setParameter("username", findPasswordDto.getUsername())
                .setParameter("phone", findPasswordDto.getPhone())
                .getResultStream().findFirst();
    }
}

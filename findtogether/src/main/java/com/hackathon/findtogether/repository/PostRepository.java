package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) { em.persist(post); }

    public Post findOne(Long id) { return em.find(Post.class,id); }

    public List<Post> findPostsByUsername(String username) {
        return em.createQuery("select p from Post p join fetch p.user where p.user.username=:username",Post.class)
                .setParameter("username",username)
                .getResultList();
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public void remove(Post post){
        em.remove(post);
    }
}

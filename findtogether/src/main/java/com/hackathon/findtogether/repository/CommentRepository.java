package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment) { em.persist(comment); }

    public Comment findOnefindOneWithCommendId(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    public Comment findOneWithPostIdAndCommendId(Long postId, Long commentId) {
        return em.createQuery("select c from Comment c where c.post.id = :postId and c.id = :commentId", Comment.class)
                .setParameter("postId", postId)
                .setParameter("commentId", commentId)
                .getSingleResult();
    }

//    //제목과 작성자를 통해 게시글 찾기
//    public Long findByTitleAndUsername(String username, String title) {
//        Post post = em.createQuery("select p from Post p join fetch p.user where p.username = :username and p.title = :title", Post.class)
//                .setParameter("username", username)
//                .setParameter("title", title)
//                .getSingleResult();
//
//        return post.getId();
//    }

//    public List<Comment> findAll(Long post_id) {
//        return em.createQuery("select c from Comment c where c.post.id = :post_id", Comment.class)
//                .setParameter("post_id", post_id)
//                .getResultList();
//    }

    public List<Comment> findAllComment(Long postId) {
        return em.createQuery(
                "select c from Comment c" +
                        " join fetch c.writer w", Comment.class)
                .getResultList();
    }

    public void remove(Comment comment){
        em.remove(comment);
    }
}

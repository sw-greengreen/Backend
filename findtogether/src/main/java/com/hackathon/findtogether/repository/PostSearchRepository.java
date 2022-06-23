package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.domain.PostType;
import com.hackathon.findtogether.domain.QPost;

import com.hackathon.findtogether.domain.ResolvingStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class PostSearchRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostSearchRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 찾아주세요
    public List<Post> findPostByLost(){
        return em.createQuery("select p " +
                "from Post p join fetch p.user where p.postType =:postType", Post.class)
                .setParameter("postType", PostType.LOST)
                .getResultList();
    }

    // 찾았어요
    public List<Post> findPostByDiscovery(){
        return em.createQuery("select p " +
                "from Post p join fetch p.user where p.postType =:postType", Post.class)
                .setParameter("postType", PostType.DISCOVERY)
                .getResultList();
    }

    public List<Post> findPostBySortType(String sortType){
        QPost p = new QPost("p");

        // 최신 순
        if (sortType.equals("LATEST")){
            List<Post> posts = queryFactory
                    .selectFrom(p)
                    .orderBy(p.createdAt.desc())
                    .fetch();
            return posts;
        }

        // 오래된 순
        else if(sortType.equals("OLDEST")){
            List<Post> posts = queryFactory
                    .selectFrom(p)
                    .orderBy(p.createdAt.asc())
                    .fetch();
            return posts;
        }
        // 해결
        else if(sortType.equals("COMPLETION")){
            List<Post> posts = queryFactory
                    .selectFrom(p)
                    .where(p.resolvingStatus.eq(ResolvingStatus.COMPLETION))
                    .orderBy(p.createdAt.desc())
                    .fetch();
            return posts;
        }

        // 미해결
        else{
            List<Post> posts = queryFactory
                    .selectFrom(p)
                    .where(p.resolvingStatus.eq(ResolvingStatus.WAITING))
                    .orderBy(p.createdAt.desc())
                    .fetch();
            return posts;
        }
    }

    // 검색 ( 제목 또는 해시태그 )
    public List<Post> findPostByKeyword(String keyword) {
        if (keyword.charAt(0) == '#'){
            return em.createQuery("select p from Post p where p.hashtag =:hashtag",Post.class)
                    .setParameter("hashtag",keyword)
                    .getResultList();

        } else{
            return em.createQuery("select p from Post p " +
                    "where p.title like CONCAT('%',:title,'%')",Post.class)
                    .setParameter("title",keyword)
                    .getResultList();
        }
    }

}

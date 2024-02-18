package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java12.controller.MainPageController;
import java12.entities.Post;
import java12.repo.PostRepo;
import java12.service.impl.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PostRepoImpl implements PostRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    @Override
    public void save(Post newPost) {
        entityManager.persist(newPost);
    }

    @Override
    public List<Post> getAllMyPosts() {
        Long userId = UserImpl.user1.getId();
        System.out.println(" THIS is :" + userId);
        return entityManager.createQuery("select p from Post p join User u on p.user.id = u.id where u.id=:userId", Post.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public void delete(Long id) {
        Post post = entityManager.find(Post.class, id);
        entityManager.remove(post);
    }

    @Override
    public void update(Post post) {
        Long id = post.getId();
        Post post1 = entityManager.find(Post.class, id);
        post1.setDescription(post.getDescription());
        post1.setTitle(post.getTitle());
        entityManager.merge(post1);
    }

    @Override
    public List<Post> getMyHomePosts() {
        return Collections.emptyList();
    }

    @Override
    public List<Post> getAllPosts() {
        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }
}

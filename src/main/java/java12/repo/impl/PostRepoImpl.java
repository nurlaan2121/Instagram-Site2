package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java12.controller.MainPageController;
import java12.entities.Follower;
import java12.entities.Post;
import java12.entities.User;
import java12.repo.PostRepo;
import java12.service.impl.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<Long> subscriptions = UserImpl.user1.getFollower().getSubscriptions();
        List<Post> homePosts = new ArrayList<>();
        List<User> followUsers = new ArrayList<>();
        List<User> users = entityManager.createQuery("select u from User u ", User.class).getResultList();
        for (int i = 0; i < subscriptions.size(); i++) {
            for (int i1 = 0; i1 < users.size(); i1++) {
                if (subscriptions.get(i).equals(users.get(i1).getId())) {
                    followUsers.add(users.get(i1));
                }
            }
        }
        for (int i = 0; i < followUsers.size(); i++) {
            List<Post> posts = entityManager.createQuery("select p from Post p where p.user.id = :userId order by createdAd desc", Post.class).setParameter("userId", followUsers.get(i)).getResultList();
            homePosts.addAll(posts);
        }
        return homePosts;
    }

    @Override
    public List<Post> getAllPosts() {
        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }
}

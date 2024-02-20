package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java12.controller.MainPageController;
import java12.entities.*;
import java12.repo.PostRepo;
import java12.service.impl.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
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

    @Transactional
    @Override
    public void delete(Long id) {
        int y = entityManager.createQuery("delete from Comment c where c.post.id = :idPost").setParameter("idPost", id).executeUpdate();
        int r = entityManager.createQuery("delete from Like l where l.post.id = :idPost").setParameter("idPost", id).executeUpdate();
        int i = entityManager.createQuery("delete from Post p where p.id = :idPost").setParameter("idPost", id).executeUpdate();
        System.out.println(i);
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
        return entityManager.createQuery("SELECT DISTINCT p FROM Post p " +
                        "LEFT JOIN FETCH p.comments " +
                        "LEFT JOIN FETCH p.likes " +
                        "LEFT JOIN FETCH p.images " +
                        "LEFT JOIN FETCH p.user", Post.class)
                .getResultList();

//        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Override
    public void like(Long idPost) {
        Like like = new Like();
        Post post = entityManager.find(Post.class, idPost);
        like.setIsLike(true);
        like.setPost(post);
        like.setUser(UserImpl.user1);
        post.getLikes().add(like);
        entityManager.persist(like);
        entityManager.merge(post);
    }

    @Override
    public List<Post> getLikedPost() {
        Long id = UserImpl.user1.getId();
        return entityManager.createQuery("select p from Post p join Like l on p.id = l.post.id where l.user.id = :userId", Post.class).setParameter("userId", id).getResultList();
    }

    @Override
    public boolean check(Long postId) {
        Like singleResult = entityManager.createQuery("select l from Like l where l.post.id = :postId and l.user.id = :userId", Like.class).setParameter("postId", postId).setParameter("userId", UserImpl.user1.getId()).getSingleResult();
        return singleResult != null;
    }

    @Override
    public void unLike(Long postId) {
        Post currentPost = entityManager.createQuery("select p from Post p where p.id = :postId", Post.class).setParameter("postId", postId).getSingleResult();
        int i = entityManager.createQuery("delete  from Like l where l.post.id = :postId and l.user.id = :userId").setParameter("postId", postId).setParameter("userId", UserImpl.user1.getId()).executeUpdate();
        for (int j = 0; j < currentPost.getLikes().size(); j++) {
            if (currentPost.getLikes().get(j).getUser().getId().equals(UserImpl.user1.getId())) {
                currentPost.getLikes().remove(j);
                break;
            }
        }
        entityManager.merge(currentPost);
    }

    @Override
    public void comment(Long postId, String commentText) {
        Comment comment = new Comment();
        comment.setComment(commentText);
        Post currentPost = entityManager.createQuery("select p from Post p where p.id = :idPost", Post.class).setParameter("idPost", postId).getSingleResult();
        comment.setPost(currentPost);
        comment.setUser(UserImpl.user1);
        comment.setCreatedAd(Date.valueOf(LocalDate.now()));
        entityManager.persist(comment);
        currentPost.getComments().add(comment);
        entityManager.merge(currentPost);
    }
}

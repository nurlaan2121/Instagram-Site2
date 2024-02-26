package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java12.dtoes.CommentDTO;
import java12.dtoes.UserDTO;
import java12.entities.Follower;
import java12.entities.Post;
import java12.entities.User;
import java12.entities.UserInfo;
import java12.exceptions.NotFoundException;
import java12.repo.UserRepo;
import java12.service.impl.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Repository
public class UserRepoImpl implements UserRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveUser(User newUser) {
        entityManager.persist(newUser);
    }

    @Override
    public User signIn(User user) throws NotFoundException {
        List<User> allUser = getAllUser();
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getUserName().equalsIgnoreCase(user.getUserName()) &&
                    allUser.get(i).getPassword().equalsIgnoreCase(user.getPassword())) {

                return allUser.get(i);
            }
        }
        throw new NotFoundException("Email or Password in correct");
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getCurrentUser(User user) {
        Long idUSer = user.getId();
        return entityManager.find(User.class, idUSer);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getMySubscription() {
        List<User> users = new ArrayList<>();
        Follower currentFollower = entityManager.createQuery("select f from Follower f where f.id = :followerId", Follower.class).setParameter("followerId", UserImpl.user1.getFollower().getId()).getSingleResult();
        List<Long> subscriptions = currentFollower.getSubscriptions();
        for (int i = 0; i < subscriptions.size(); i++) {
            User fori = entityManager.createQuery("select u from User u where u.id = :fori", User.class).setParameter("fori", subscriptions.get(i)).getSingleResult();
            users.add(fori);
        }
        return users;
    }

    @Override
    public List<User> search(String keyword) {
        return entityManager.createQuery("select u from User u join Follower f on u.follower.id  = f.id where u.email ilike (:key) or u.userName ilike (:key) or u.userInfo.fullName ilike (:key)", User.class).setParameter("key", keyword).getResultList();
    }

    @Override
    public void subscribe(Long userId) {
        List<Long> subscriptions = UserImpl.user1.getFollower().getSubscriptions();
        boolean isYeas = false;
        for (int i = 0; i < subscriptions.size(); i++) {
            if (subscriptions.get(i).equals(userId)) {
                User findUser = entityManager.createQuery("select u from User u join Follower f on u.follower.id = f.id where u.id = :userId", User.class).setParameter("userId", userId).getSingleResult();
                subscriptions.remove(i);
                for (int i1 = 0; i1 < findUser.getFollower().getSubscribers().size(); i1++) {
                    if (findUser.getFollower().getSubscribers().get(i).equals(UserImpl.user1.getId())) {
                        findUser.getFollower().getSubscribers().remove(i);
                    }
                }
                entityManager.merge(findUser);
                entityManager.merge(UserImpl.user1);
                isYeas = true;
                break;
            }
        }
        if (!isYeas) {
            User findUser = entityManager.createQuery("select u from User u join Follower f on u.follower.id = f.id where u.id = :userId", User.class).setParameter("userId", userId).getSingleResult();
            subscriptions.add(userId);
            findUser.getFollower().getSubscribers().add(UserImpl.user1.getId());
            entityManager.merge(findUser);
            entityManager.merge(UserImpl.user1.getFollower());
        }
    }

    @Override
    public User profileFindUser(Long userId) {
        return entityManager.createQuery("select u from User u join Follower f on u.follower.id = f.id join UserInfo i on u.userInfo.id = i.id where u.id = :userId", User.class).setParameter("userId", userId).getSingleResult();

    }

    @Override
    public List<Post> getAllPostFindUser(Long userId) {
        return entityManager.createQuery("select p from Post p where p.user.id = :userId", Post.class).setParameter("userId", userId).getResultList();

    }

    @Override
    public List<User> findUserGetAllSubscriptions(Long userId) {
        List<User> users = new ArrayList<>();
        Follower follower = entityManager.createQuery("select f from  Follower f join User u on f.id = u.follower.id where u.id = :userId", Follower.class).setParameter("userId", userId).getSingleResult();
        for (int i = 0; i < follower.getSubscriptions().size(); i++) {
            User userId1 = entityManager.createQuery("select u from User u where u.id = :userId", User.class).setParameter("userId", follower.getSubscriptions().get(i)).getSingleResult();
            users.add(userId1);
        }
        return users;
    }

    @Override
    public List<User> findUserGetAllSubscribers(Long userId) {
        List<User> users = new ArrayList<>();
        Follower follower = entityManager.createQuery("select f from  Follower f join User u on f.id = u.follower.id where u.id = :userId", Follower.class).
                setParameter("userId", userId).getSingleResult();
        for (int i = 0; i < follower.getSubscribers().size(); i++) {
            User userId1 = entityManager.createQuery("select u from User u where u.id = :userId", User.class).
                    setParameter("userId", follower.getSubscribers().get(i)).
                    getSingleResult();
            users.add(userId1);
        }
        return users;
    }

    @Override
    public UserDTO getMyInfo() {
        UserDTO currentUser = entityManager.createQuery("select new java12.dtoes.UserDTO(u.id,u.userName,u.email,u.phoneNumber) from User u where u.id = :userId", UserDTO.class).setParameter("userId", UserImpl.user1.getId()).getSingleResult();
        UserInfo userInfo = entityManager.createQuery("select i from UserInfo i join User u on u.userInfo.id = i.id where u.id = :userId", UserInfo.class).setParameter("userId", UserImpl.user1.getId()).getSingleResult();
        currentUser.setBio(userInfo.getBiography());
        currentUser.setFullName(userInfo.getFullName());
        currentUser.setProfileLink(userInfo.getImageProfile());
        return currentUser;
    }
}

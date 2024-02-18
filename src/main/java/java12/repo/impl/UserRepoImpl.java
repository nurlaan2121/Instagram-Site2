package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java12.entities.User;
import java12.exceptions.NotFoundException;
import java12.repo.UserRepo;
import java12.service.impl.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        return entityManager.find(User.class,idUSer);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getMySubscription() {
        Long followerId = UserImpl.user1.getFollower().getId();
        return Collections.emptyList();
    }
}

package java12.service.impl;

import java12.dtoes.UserDTO;
import java12.entities.Follower;
import java12.entities.Post;
import java12.entities.User;
import java12.entities.UserInfo;
import java12.exceptions.NotFoundException;
import java12.repo.UserRepo;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserImpl implements UserInterface {
    private final UserRepo userRepo;
    public static User user1;

    @Override
    public void saveUser(User newUser) {
        Follower follower = new Follower();
        UserInfo userInfo = new UserInfo();
        newUser.setFollower(follower);
        newUser.setUserInfo(userInfo);

        userRepo.saveUser(newUser);
    }

    @Override
    public void signIn(User user) throws NotFoundException {
        user1 = userRepo.signIn(user);
        System.out.println(user1.getId() + "  " + user.getUserName());
    }

    @Override
    public User getCurrentUser() {
        return userRepo.getCurrentUser(user1);
    }

    @Override
    public void updateUser(User user) {
        userRepo.update(user);
    }

    @Override
    public List<User> getMySubscription() {
        return userRepo.getMySubscription();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUser();
    }

    @Override
    public List<User> search(String keyword) {
        return userRepo.search("%" + keyword + "%");
    }

    @Override
    public void subscribe(Long userId) {
        userRepo.subscribe(userId);
    }

    @Override
    public User profile(Long userId) {
        return userRepo.profileFindUser(userId);
    }

    @Override
    public List<Post> getAllPostFindUser(Long userId) {
        return userRepo.getAllPostFindUser(userId);
    }

    @Override
    public List<User> findUserGetAllSubscriptions(Long userId) {
        return userRepo.findUserGetAllSubscriptions(userId);
    }

    @Override
    public List<User> findUserGetAllSubscribers(Long userId) {
        return userRepo.findUserGetAllSubscribers(userId);
    }

    @Override
    public UserDTO getMyInfo() {
        return userRepo.getMyInfo();
    }
}

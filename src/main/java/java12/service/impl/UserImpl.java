package java12.service.impl;

import java12.entities.Follower;
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
    public User signIn(User user) {
        try {
            user1 = userRepo.signIn(user);
            System.out.println(user1.getId()+ "  " + user.getUserName());
        } catch (NotFoundException e) {
            System.out.println("USERIMPL EXCEP");
        }
        return user1;
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
}

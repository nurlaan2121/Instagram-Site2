package java12.service;

import java12.entities.User;

import java.util.List;

public interface UserInterface {
    void saveUser(User newUser);

    User signIn(User user);
    User getCurrentUser();

    void updateUser(User user);

    List<User> getMySubscription();
}

package java12.service;

import java12.entities.Post;
import java12.entities.User;
import java12.exceptions.NotFoundException;

import java.util.List;

public interface UserInterface {
    void saveUser(User newUser);

    void signIn(User user) throws NotFoundException;
    User getCurrentUser();

    void updateUser(User user);

    List<User> getMySubscription();

    List<User> getAllUsers();

    List<User> search(String keyword);

    void subscribe(Long userId);

    User profile(Long userId);

    List<Post> getAllPostFindUser(Long userId);

    List<User> findUserGetAllSubscriptions(Long userId);

    List<User> findUserGetAllSubscribers(Long userId);
}

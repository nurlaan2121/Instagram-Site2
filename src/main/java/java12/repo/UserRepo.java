package java12.repo;

import java12.entities.Post;
import java12.entities.User;
import java12.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo {

    void saveUser(User newUser);

    User signIn(User user) throws NotFoundException;
    List<User> getAllUser();
    User getCurrentUser(User user);

    void update(User user);

    List<User> getMySubscription();

    List<User> search(String keyword);

    void subscribe(Long userId);

    User profileFindUser(Long userId);

    List<Post> getAllPostFindUser(Long userId);

    List<User> findUserGetAllSubscriptions(Long userId);

    List<User> findUserGetAllSubscribers(Long userId);
}

package java12.repo;

import java12.entities.Follower;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepo {
    Follower getFollowerById(Long id);
}

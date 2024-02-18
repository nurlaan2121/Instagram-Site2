package java12.service;

import java12.entities.Follower;
import org.springframework.stereotype.Service;

@Service
public interface FollowerInterface {
    Follower getFollowerById();
}

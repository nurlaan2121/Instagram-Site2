package java12.service.impl;

import java12.controller.MainPageController;
import java12.entities.Follower;
import java12.repo.FollowerRepo;
import java12.service.FollowerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerImpl implements FollowerInterface {
    private final FollowerRepo followerRepo;
    @Override
    public Follower getFollowerById() {
        Long idFollower = UserImpl.user1.getFollower().getId();
        return followerRepo.getFollowerById(idFollower);
    }
}

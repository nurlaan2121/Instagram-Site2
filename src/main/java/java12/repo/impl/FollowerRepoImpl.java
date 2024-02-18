package java12.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java12.entities.Follower;
import java12.repo.FollowerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@RequiredArgsConstructor
@Transactional
public class FollowerRepoImpl implements FollowerRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Follower getFollowerById(Long id) {
        return entityManager.find(Follower.class,id);
    }
}

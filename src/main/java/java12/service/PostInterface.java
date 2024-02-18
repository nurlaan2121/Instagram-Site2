package java12.service;

import java12.entities.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface PostInterface {
    void save(Post newPost);

    List<Post> getAllMyPosts();

    void deletePost(Long id);

    void update(Post post);

    List<Post> getMyHomePosts();

    List<Post> getAllPosts();
}

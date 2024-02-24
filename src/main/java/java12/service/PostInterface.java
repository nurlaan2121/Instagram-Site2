package java12.service;

import java12.dtoes.PostDTO;
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

    List<PostDTO> getAllPosts();

    void likePost(Long idPost);

    List<Post> getLikedPost();

    boolean isPostLikedByCurrentUser(Long postId);

    void unlikePost(Long postId);

    void addComment(Long postId, String commentText);

    List<PostDTO> search(String keyword);

}

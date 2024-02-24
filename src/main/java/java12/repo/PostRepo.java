package java12.repo;

import java12.dtoes.PostDTO;
import java12.entities.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo {
    void save(Post newPost);

    List<Post> getAllMyPosts();

    void delete(Long id);

    void update(Post post);

    List<Post> getMyHomePosts();

    List<PostDTO> getAllPosts();


    void like(Long idPost);

    List<Post> getLikedPost();

    boolean check(Long postId);

    void unLike(Long postId);

    void comment(Long postId, String commentText);

    List<PostDTO> search(String keyword);
}

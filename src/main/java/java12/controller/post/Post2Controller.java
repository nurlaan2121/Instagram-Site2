package java12.controller.post;

import jakarta.servlet.http.HttpServletResponse;
import java12.entities.Post;
import java12.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
@RequiredArgsConstructor

public class Post2Controller {
    private final PostInterface postInterface;

    @GetMapping("/getPosts")
    @ResponseBody
    public List<Post> getPosts() {
        return postInterface.getAllPosts();
    }

    @GetMapping("/getLikedPosts")
    @ResponseBody
    public List<Post> getLikedPosts() {
        return postInterface.getLikedPost();
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable Long id) {
        postInterface.likePost(id);
        return "redirect:/search";
    }

    @PostMapping("/comment/{id}")
    public String comment(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String commentText = requestBody.get("commentText");
        postInterface.addComment(id, commentText);
        return "redirect:/search";
    }

    @DeleteMapping("/like/{postId}")
    @ResponseBody
    public ResponseEntity<String> unlikePost(@PathVariable Long postId) {
        // Проверьте, есть ли у пользователя лайк для данного поста
        boolean isLiked = postInterface.isPostLikedByCurrentUser(postId);

        if (isLiked) {
            // Удалите лайк из базы данных
            postInterface.unlikePost(postId);
            return new ResponseEntity<>("Post unliked successfully", HttpStatus.OK);
        } else {
            // Если лайка нет, верните соответствующий статус
            return new ResponseEntity<>("Post is not liked by the current user", HttpStatus.BAD_REQUEST);
        }
    }
}

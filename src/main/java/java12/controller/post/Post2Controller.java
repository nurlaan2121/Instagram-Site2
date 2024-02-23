package java12.controller.post;

import jakarta.servlet.http.HttpServletResponse;
import java12.dtoes.PostDTO;
import java12.entities.Post;
import java12.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
@RequiredArgsConstructor

public class Post2Controller {
    @Autowired
    private final PostInterface postInterface;

    @GetMapping("/getPosts")
    @ResponseBody
    public List<PostDTO> getPosts() {
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
}

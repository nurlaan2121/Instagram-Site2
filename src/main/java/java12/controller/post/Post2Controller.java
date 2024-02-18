package java12.controller.post;

import java12.entities.Post;
import java12.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}

package java12.controller;

import java12.entities.Post;
import java12.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomePageController {
    private final PostInterface postInterface;
    @GetMapping("/home")
    public String getAll(Model model) {
        List<Post> all = postInterface.getMyHomePosts();
        model.addAttribute("myHomePosts", all);
        return "home-page";
    }
    @GetMapping("/home2")
    @ResponseBody
    public List<Post> getPosts() {
        return postInterface.getMyHomePosts();
    }
}

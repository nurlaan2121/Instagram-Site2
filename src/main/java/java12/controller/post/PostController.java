package java12.controller.post;

import java12.entities.Post;
import java12.entities.User;
import java12.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("create/post")
@RequiredArgsConstructor
public class PostController {
    private final PostInterface postInterface;

    @GetMapping()
    public String mainPage2() {
        return "create-post";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        Post post = new Post();
        post.setImages(new ArrayList<>());
        model.addAttribute("newPost", post);
        return "create-post";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("newPostt") Post post) {
        postInterface.save(post);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        postInterface.deletePost(id);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        Post oldPost = postInterface.getAllMyPosts().stream().filter(post -> post.getId().equals(id)).findFirst().get();
        model.addAttribute("oldPost", oldPost);
        return "postUpdate-page";
    }

    @PostMapping("/update2")
    public String update2(@ModelAttribute("oldPost") Post post) {
        postInterface.update(post);
        return "redirect:/home";
    }

}

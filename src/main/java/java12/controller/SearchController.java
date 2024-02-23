package java12.controller;

import java12.dtoes.PostDTO;
import java12.entities.Post;
import java12.entities.User;
import java12.service.PostInterface;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class SearchController {
    private final PostInterface postInterface;
    private final UserInterface userInterface;

    @GetMapping("searchу")
    public String search() {
        return "search-page";
    }

    @GetMapping("/search")
    public String showPosts(Model model) {
        List<PostDTO> posts = postInterface.getAllPosts();
        model.addAttribute("posts", posts);
        return "search-page";
    }

    @GetMapping("/searchUser")
    public String showPostsUser() {
        return "searchUser-page";
    }
    @GetMapping("/users/search")
    String searchUsers(@RequestParam String keyword, Model model){
        List<User> searchUsers = userInterface.search(keyword);
        model.addAttribute("searchUsers", searchUsers);
        return "searchUser-page";
    }


}

package java12.controller;

import java12.entities.Post;
import java12.service.PostInterface;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class SearchController {
    private final PostInterface postInterface;
    private final UserInterface userInterface;
    @GetMapping("searchу")
    public String search(){
        return "search-page";
    }
    @GetMapping("search")
    public String showSearchPage(Model model) {
        List<Post> posts = postInterface.getAllPosts();
        model.addAttribute("posts", posts);
        return "search-page"; // Убедитесь, что у вас есть HTML-файл с таким именем
    }
}

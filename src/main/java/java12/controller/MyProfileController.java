package java12.controller;

import java12.entities.Follower;
import java12.entities.Post;
import java12.service.FollowerInterface;
import java12.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MyProfileController {
    private final PostInterface postInterface;
    private final FollowerInterface followerInterface;
    @RequestMapping("/myProfile")
    public String myProfile(Model follower,Model myAllPosts){
        Follower follower1 = followerInterface.getFollowerById();
        follower.addAttribute("follower",follower1);
        List<Post> allMyPosts = postInterface.getAllMyPosts();
        myAllPosts.addAttribute("myAllPosts",allMyPosts);
        return "myProfile-page";
    }
}

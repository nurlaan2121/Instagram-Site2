package java12.controller.user;

import java12.dtoes.CommentDTO;
import java12.entities.Post;
import java12.entities.User;
import java12.entities.UserInfo;
import java12.service.PostInterface;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserInterface userInterface;
    private final PostInterface postInterface;
    @GetMapping("update/user")
    public String update(Model model){
        User user  = userInterface.getCurrentUser();
        model.addAttribute("currentUSer",user);
        return "updateUser-page";
    }
    @PostMapping("/update2")
    public String update2(@ModelAttribute("currentUser") User user){
        userInterface.updateUser(user);
        return "redirect:/myProfile";
    }
//    public String mySubscriptions(Model model){
//        List<User> subscription = userInterface.getMySubscription();
//    }
    @GetMapping("/subscribe/{userId}")
    public String subscribe(@PathVariable Long userId){
        userInterface.subscribe(userId);
        return "redirect:/searchUser";
    }
    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable Long userId,Model model,Model postModel){
        User user = userInterface.profile(userId);
        List<Post> posts = userInterface.getAllPostFindUser(userId);
        postModel.addAttribute("findUserPosts",posts);
        model.addAttribute("profileUser",user);
        return "viewProfile";
    }
    @GetMapping("/subscribers/{userId}")
    public String subscribers(@PathVariable Long userId,Model model){
        List<User> users = userInterface.findUserGetAllSubscribers(userId);
        model.addAttribute("allSubscribers",users);
        return "subscribers-page";
    }
    @GetMapping("/subscriptions/{userId}")
    public String subscriptions(@PathVariable Long userId,Model model){
        List<User> users = userInterface.findUserGetAllSubscriptions(userId);
        model.addAttribute("allSubscriptions",users);
        return "subscriptions-page";
    }
    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id) {
        postInterface.likePost(id);
        return "redirect:/search";
    }
    @GetMapping("/info/{id}")
    public String info(@PathVariable Long id,Model model) {
        List<CommentDTO> commentDTO = postInterface.infoAboutCurrentPost(id);
        model.addAttribute("infoPost",commentDTO);
        return "infoComment-page";
    }

    @PostMapping("/comment/{postId}")
    public String addComment(@PathVariable Long postId, @RequestParam String comment) {
        postInterface.addComment(postId,comment);
        return "redirect:/search";
    }
    @PostMapping("/likeComment/{commentId}")
    public String likeComment(@PathVariable Long commentId) {
        postInterface.addLikeToComment(commentId);
        System.out.println("!!!!!!!!!!!!!!!!!");
        return "redirect:/search";
    }


}

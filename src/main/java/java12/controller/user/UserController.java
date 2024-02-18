package java12.controller.user;

import java12.entities.User;
import java12.entities.UserInfo;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserInterface userInterface;
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
}

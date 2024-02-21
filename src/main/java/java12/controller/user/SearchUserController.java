package java12.controller.user;

import java12.entities.User;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class SearchUserController {
    private final UserInterface userInterface;


    @GetMapping("/getFollows")
    @ResponseBody
    public List<User> getFollows() {
        return userInterface.getMySubscription();
    }
    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<User> getAllSUsers() {
        return userInterface.getAllUsers();
    }
}

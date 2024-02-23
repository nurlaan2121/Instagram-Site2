package java12.controller;

import java12.entities.User;
import java12.exceptions.NotFoundException;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainPageController {

    private final UserInterface userInterface;
    @GetMapping("re")
    public String mainPage2(Model model) {
        model.addAttribute("newUser", new User());
        return "main-page";
    }
    @GetMapping()
    public String createUser(Model model){
        model.addAttribute("newUser", new User());
        return "main-page";
    }
    @PostMapping("/signin")
    public String save(@ModelAttribute("newStudent") User user){
        try {
            userInterface.signIn(user);
        } catch (NotFoundException e) {
            return "error-page";
        }
        return "redirect:/home";
    }
}

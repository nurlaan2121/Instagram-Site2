package java12.controller;

import java12.entities.User;
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
@RequestMapping("registr/page")
public class RegistrPageController {
    private final UserInterface userInterface;
    @GetMapping()
    public String mainPage2() {
        return "registr-page";
    }
    @GetMapping("/new")
    public String createUser(Model model){
        model.addAttribute("newUser", new User());
        return "registr-page";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("newStudent") User user){
        userInterface.saveUser(user);
        return "redirect:/";
    }
}

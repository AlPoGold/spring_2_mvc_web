package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    //TODO @GetMapping("user-delete/{id}")
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }


    @PostMapping("/user-update")
    public String updateUser(
            @RequestParam int id,
            @ModelAttribute("user") User changedUser) {

        userService.updateById(id, changedUser);
        return "redirect:/users";
    }

}

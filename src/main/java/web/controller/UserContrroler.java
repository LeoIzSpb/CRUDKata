package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.DAO.UserDao;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserContrroler {

    private final UserDao userDao;
    private final UserService userService;

    @Autowired
    public UserContrroler(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "users";
    }

    @GetMapping("/show")
    public String showUser(@RequestParam(value = "id") int id,Model model) {
        model.addAttribute("user",userService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "add_user";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_user";
        } else {
            userService.addUser(user);
            return "redirect:/";
        }
    }

    @PostMapping ("/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateUser(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            userService.updateUser(user);
            return "redirect:/";
        }
    }

}
// В приложении должна быть страница, на которую выводятся все юзеры с возможностью добавлять, удалять и изменять юзера.
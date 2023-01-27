package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.DAO.UserDao;
import web.model.User;

import javax.validation.Valid;

@Controller
public class UserContrroler {

    private final UserDao userDao;

    @Autowired
    public UserContrroler(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users",userDao.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id,Model model) {
        model.addAttribute("user",userDao.getUserById(id));
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
            userDao.addUser(user);
            return "redirect:/";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("edit/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute(userDao.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit")
    public String update(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            userDao.updateUser(user);
            return "redirect:/";
        }
    }

}
// В приложении должна быть страница, на которую выводятся все юзеры с возможностью добавлять, удалять и изменять юзера.
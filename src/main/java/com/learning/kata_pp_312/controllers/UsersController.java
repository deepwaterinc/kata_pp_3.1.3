package com.learning.kata_pp_312.controllers;

import com.learning.kata_pp_312.entities.User;
import com.learning.kata_pp_312.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UsersController {
    private UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }



    // по дефолту открыта страница /users
    @GetMapping("/admin/users")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/show-user")
    public String userForUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user-page";
    }

    //поиск по id
    @GetMapping("/users/{id}")
    public String findUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-page";
    }

    //Создаем объект user и направляем на страницу с формой
    @GetMapping("/admin/users/user-create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "create-form";
    }
    //Отправляем форму в виде post-запроса с введенными полями на /users, добавляем юзера в БД и обратно направляем на /users
    @PostMapping("/admin/users")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    //запрос по адресу /users/{id}/user-edit. Читаем id и записываем в модель юзера с подходящим id.
    //направляем patch запрос на адрес /users/{id}.
    @GetMapping("/admin/users/{id}/user-edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit-form";
    }
    //получаем patch запрос(который отфильтровался с помощью registerHiddenFieldFilter() в AppInit)
    //создаем объект User и записываем в него из patch запроса данные.
    //обновляем юзера из БД
    @PatchMapping("/admin/users/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}/user-delete")
    public String deleteUser(@PathVariable("id") int id ) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}

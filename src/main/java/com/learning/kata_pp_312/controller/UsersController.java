package com.learning.kata_pp_312.controller;

import com.learning.kata_pp_312.model.User;
import com.learning.kata_pp_312.service.UserService;
import com.learning.kata_pp_312.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // по дефолту открыта страница /users
    @GetMapping("/")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users";
    }

    //поиск по id
    @GetMapping("/users/{id}")
    public String findUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-page";
    }

    //Создаем объект user и направляем на страницу с формой
    @GetMapping("/users/user-create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "create-form";
    }
    //Отправляем форму в виде post-запроса с введенными полями на /users, добавляем юзера в БД и обратно направляем на /users
    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    //запрос по адресу /users/{id}/user-edit. Читаем id и записываем в модель юзера с подходящим id.
    //направляем patch запрос на адрес /users/{id}.
    @GetMapping("/users/{id}/user-edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit-form";
    }
    //получаем patch запрос(который отфильтровался с помощью registerHiddenFieldFilter() в AppInit)
    //создаем объект User и записываем в него из patch запроса данные.
    //обновляем юзера из БД
    @PatchMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("users/{id}/user-delete")
    public String deleteUser(@PathVariable("id") int id ) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}

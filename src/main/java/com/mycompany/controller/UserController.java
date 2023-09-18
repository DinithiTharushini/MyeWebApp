package com.mycompany.controller;

import com.mycompany.model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.mycompany.Exception.UserNotFoundException;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService services;
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listusers= services.ListAll();
        model.addAttribute("listusers",listusers);

        return "users";
    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("PageTitle","Add new user");
        return "user_form";
    }
    @PostMapping("users/save")
    public String saveUser(User user, RedirectAttributes ra){
        services.save(user);
        ra.addFlashAttribute("message","user has beeen saved succcessfully");

        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id")int id,Model model,RedirectAttributes ra){
        try {
            User user=services.get(id);
            model.addAttribute("user",user);
            model.addAttribute("PageTitle","Edit User (ID :"+id+")");
            return "/user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("meaasage","the user has been edited succesfully");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteentry(@PathVariable("id")int id,RedirectAttributes ra){
        try {
            services.delete(id);
            ra.addFlashAttribute("message","The user has been deleted successfully");
            return "redirect:/users";

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","The user has not been found");
            return "redirect:/users";
        }
    }


}

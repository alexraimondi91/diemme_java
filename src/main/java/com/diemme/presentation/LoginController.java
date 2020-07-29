package com.diemme.presentation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.EmailService;
import com.diemme.business.RoleService;
import com.diemme.business.impl.UserService;
import com.diemme.domain.Role;
import com.diemme.domain.User;

@Controller
public class LoginController {
	
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    
	@Autowired
	EmailService emailService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login/login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("auth/login/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        Role roleUser = roleService.findByRole("CLIENT");
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleUser);
        user.setRoles(roles);
        user.setActive(false);
        System.out.println("user: \n\n" + user);
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "Questo nome utente è già stato preso, riprova con un altro!");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("\n\n bindingResult: \n\n" + bindingResult.getAllErrors());
            modelAndView.setViewName("auth/login/registration");
        } else {
            userService.saveUser(user);
            System.out.println("user after save: \n\n" + user);

            modelAndView.addObject("successMessage", "Ti sei appena registrato! ora attendi un'email di attivazione prima di poter accedere alla tua area personale!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("auth/login/registration");
            emailService.sendUserActive(user.getEmail(), user.getName());

        }
        return modelAndView;
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        //modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getSurname() + " (" + user.getEmail() + ")");
        //modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("frontoffice/home/home");
        return modelAndView;
    }
}
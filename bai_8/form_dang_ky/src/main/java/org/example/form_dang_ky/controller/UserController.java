package org.example.form_dang_ky.controller;

import jakarta.validation.Valid;
import org.example.form_dang_ky.dto.UserDto;
import org.example.form_dang_ky.entity.User;
import org.example.form_dang_ky.service.IUserService;
import org.example.form_dang_ky.validate.UserValidate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showList(@PageableDefault(page = 0, size = 2, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                           @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
                           ModelMap model) {
        Page<User> userPage = userService.findAllByNameContaining(searchName, pageable);
        model.addAttribute("userPage", userPage);
        model.addAttribute("searchName", searchName);
        return "user/list";
    }

    @GetMapping("/add")
    public String createUsers(ModelMap model) {
        model.addAttribute("userDto", new UserDto());
        return "user/add";
    }

    @PostMapping("/add")
    public String createUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, RedirectAttributes redirect) {
        UserValidate userValidate = new UserValidate();
        userValidate.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/add";
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        String mess = "Sign up success";
        try {
            userService.addUser(user);
        } catch (Exception e) {
            mess = "Sign up fail!";
        }
        redirect.addFlashAttribute("mess", mess);
        return "redirect:/users";
    }
}

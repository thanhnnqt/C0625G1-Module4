package org.example.bai_tap_1.controller;

import org.example.bai_tap_1.entity.Blog;
import org.example.bai_tap_1.service.IBlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService blogService;

    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

    // Hiển thị danh sách blog với phân trang và tìm kiếm
    @GetMapping("")
    public String showList(@PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                           @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
                           ModelMap model) {
        Page<Blog> blogPage = blogService.findAllByNameContaining(searchName, pageable);
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("searchName", searchName);
        return "blog/list";
    }

    // Trang thêm blog mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/add";
    }

    // Xử lý thêm blog
    @PostMapping("/add")
    public String createBlog(@ModelAttribute("blog") Blog blog,
                             BindingResult bindingResult,
                             RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "blog/add";
        }
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/blogs";
    }

    // Trang chi tiết blog
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Integer id, ModelMap model, RedirectAttributes redirect) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirect.addFlashAttribute("mess", "Không tìm thấy blog");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/details";
    }

    // Trang sửa blog
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirect) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirect.addFlashAttribute("mess", "Không tìm thấy blog");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/update";
    }

    // Xử lý cập nhật blog
    @PostMapping("/update")
    public String updateBlog(@ModelAttribute Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("mess", "Cập nhật thành công");
        return "redirect:/blogs";
    }
    
}

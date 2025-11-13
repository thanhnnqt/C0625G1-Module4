package org.example.bai_tap_1.controller;

import org.example.bai_tap_1.entity.Blog;
import org.example.bai_tap_1.service.IBlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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

    // ---------------- LIST ----------------
    @GetMapping("")
    public String showList(@PageableDefault(page = 0, size = 2, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                           @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
                           ModelMap model) {
        Page<Blog> blogPage = blogService.findAllByNameContaining(searchName, pageable);
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("searchName", searchName);
        return "blog/list";
    }

    // ---------------- ADD ----------------
    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    @GetMapping("/add")
    public String createBlogs(ModelMap model) {
        model.addAttribute("blog", new Blog());
        return "blog/add";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
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

    // ---------------- DETAILS ----------------
    @GetMapping("/details/{id}")
    public String getDetailBlogById(@PathVariable Integer id, ModelMap model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/details";
    }

    // ---------------- UPDATE ----------------
    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable("id") int id,
                               ModelMap model,
                               RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy blog");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/update";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    @PostMapping("/update")
    public String update(@ModelAttribute Blog blog,
                         RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
        return "redirect:/blogs";
    }
}

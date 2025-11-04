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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showList(@PageableDefault(page = 0, size = 2, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                           @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
                           ModelMap model) {
        Page<Blog> blogPage = blogService.findAllByNameContaining(searchName, pageable);
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("searchName", searchName);
        return "blog/list";
    }

    @GetMapping("/add")
    public String createBlogs(ModelMap model) {
        model.addAttribute("blog", new Blog());
        return "blog/add";
    }

    @PostMapping("/add")
    public String createBlog(@ModelAttribute("blog") Blog blog, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "blog/add";
        }
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/blogs";
    }

    @GetMapping("/details/{id}")
    public String getDetailBlogById(@PathVariable Integer id, ModelMap model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/details";
    }

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("mess", "Không tìm thấy blog");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("mess", "Cập nhật thành công");
        return "redirect:/blogs";
    }

}

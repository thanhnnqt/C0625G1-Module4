package org.example.bai_tap_1.controller;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.example.bai_tap_1.entity.Blog;
import org.example.bai_tap_1.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showList(Model model) {
        List<Blog> blogList = blogService.findAll();
        model.addAttribute("blogList", blogList);
        return "blog/list";
    }

    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("blogs", new Blog());
        return "blog/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Blog blog,
                       RedirectAttributes redirectAttributes) {
        blogService.add(blog);
        redirectAttributes.addFlashAttribute("mess", "Thêm mới thành công");
        return "redirect:/blogs";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Blog> results = blogService.search(keyword);
        model.addAttribute("blogList", results);
        model.addAttribute("keyword", keyword);
        return "blog/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        blogService.delete(id);
        redirectAttributes.addFlashAttribute("mess", "Xóa thành công");
        return "blog/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("mess", "Không tìm thấy blog");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        blogService.update(blog);
        redirectAttributes.addFlashAttribute("mess", "Cập nhật thành công");
        return "redirect:/blogs";
    }
}

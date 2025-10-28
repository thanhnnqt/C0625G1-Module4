package org.example.demo2.controller;

import org.example.demo2.entity.Product;
import org.example.demo2.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository = new ProductRepository();

    // Danh sách sản phẩm
    @GetMapping
    public String list(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("products", productRepository.findAll());
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "products";
    }

    // Form tạo sản phẩm mới
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // Xử lý thêm mới
    @PostMapping("/create")
    public String create(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm thành công!");
        return "redirect:/products";
    }

    // Lưu (cập nhật hoặc thêm mới)
    @PostMapping("/save")
    public String save(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        if (product.getId() == 0) {
            int newId = productRepository.findAll().stream().mapToInt(Product::getId).max().orElse(0) + 1;
            product.setId(newId);
        }
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "Thêm mới sản phẩm thành công!");
        return "redirect:/products";
    }

    // Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Product p = productRepository.findById(id);
        model.addAttribute("product", p);
        return "product-form";
    }

    // Xóa sản phẩm (GET)
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        productRepository.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Product> results = productRepository.search(keyword);
        model.addAttribute("products", results);
        model.addAttribute("keyword", keyword);
        return "products";
    }
}

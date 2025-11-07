package org.example.product_manage.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.product_manage.dto.ProductDto;
import org.example.product_manage.entity.Cart;
import org.example.product_manage.entity.CartItem;
import org.example.product_manage.entity.Product;
import org.example.product_manage.service.IProductService;
import org.example.product_manage.validate.ProductValidate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/product/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Integer id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action,
                            HttpServletResponse response) {
        Cookie cookie = new Cookie("lastAddedProduct", id.toString());
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/product/error_404";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

    @GetMapping("/add")
    public String createProduct(ModelMap model) {
        model.addAttribute("productDto", new ProductDto());
        return "product/add";
    }

//    @PostMapping("/add")
//    public String createProduct(@Valid @ModelAttribute("productDto") ProductDto productDto, BindingResult bindingResult, RedirectAttributes redirect) {
//        ProductValidate productValidate = new ProductValidate();
//        productValidate.validate(productDto, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "product/add";
//        }
//        Product product = new Product();
//        BeanUtils.copyProperties(productDto, product);
//        String mess = "Sign up success";
//        try {
//            productService.addProduct(product);
//        } catch (Exception e) {
//            mess = "Sign up fail!";
//        }
//        redirect.addFlashAttribute("mess", mess);
//        return "redirect:/shop";
//    }

    @PostMapping("/add")
    public String addProduct(
            @Valid @ModelAttribute("productDto") ProductDto dto,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile imageFile,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        String fileName = null;
        try {
            if (!imageFile.isEmpty()) {
                fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/images/" + fileName);
                Files.copy(imageFile.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        if (fileName != null) {
            product.setImageUrl("/images/" + fileName);
        }
        productService.save(product);
        redirectAttributes.addFlashAttribute("msg", "Thêm sản phẩm thành công!");
        return "redirect:/shop";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {
        Product p = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        model.addAttribute("product", p);
        return "/product/details";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        ProductDto dto = new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImageUrl()
        );

        model.addAttribute("productDto", dto);
        model.addAttribute("id", id);

        return "/product/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(
            @PathVariable Integer id,
            @Valid @ModelAttribute("productDto") ProductDto dto,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile imageFile) {

        if (bindingResult.hasErrors()) {
            return "/product/edit";
        }

        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());

        try {
            if (!imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                product.setImageUrl("/images/" + fileName); // ảnh mới
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        productService.save(product);

        return "redirect:/shop";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return "redirect:/shop";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            cart.removeProduct(id);
        }

        session.setAttribute("cart", cart);
        return "redirect:/shopping-cart";
    }
}
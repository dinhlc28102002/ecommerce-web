package com.ecommerce.controllers.admin;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.services.CategoryService;
import com.ecommerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public String product(Model model) {
        List<Product> products = this.productService.getAll();
        model.addAttribute("products", products);
        return "admin/product/index";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("listCate", this.categoryService.getAll());
        return "admin/product/add";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product,
                             @RequestParam("fileImage") MultipartFile file) throws IOException {
        try {
            // Lưu file vào thư mục uploadDirectory
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/uploads" + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);

            // Trả về đường dẫn tới file đã upload
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage("/assets/uploads/" + file.getOriginalFilename());
        if (this.productService.create(product)) {
            return "redirect:/admin/product";
        } else {
            return "redirect:/admin/product/add-product";
        }
    }

    @GetMapping("/edit-product/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product product = this.productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("listCate", this.categoryService.getAll());
        return "admin/product/edit";
    }

    @PostMapping("/edit-product")
    public String edit(@ModelAttribute("product") Product product) {
        if (this.productService.update(product)) {
            return "redirect:/admin/product";
        }else
            return "redirect:/admin/product/edit";
    }

    @GetMapping("/delete-product/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if (this.productService.delete(id)) {
            return "redirect:/admin/product";
        }else {
            return "redirect:/admin/product";
        }
    }
}

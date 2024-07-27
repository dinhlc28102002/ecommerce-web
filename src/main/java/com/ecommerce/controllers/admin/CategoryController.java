package com.ecommerce.controllers.admin;

import com.ecommerce.models.Category;
import com.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Category> list = this.categoryService.getAll(pageNo);
        if (keyword != null) {
            list = this.categoryService.search(keyword,pageNo);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("list", list);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model) {
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category", category);

        return "admin/category/add";
    }

    @PostMapping("/add-category")
    public String add(@ModelAttribute("category") Category category) {
        if (this.categoryService.create(category)) {

            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category/add";
        }
    }

    @GetMapping("/edit-category/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Category category = this.categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/edit-category")
    public String edit(@ModelAttribute("category") Category category) {
        if (this.categoryService.update(category)) {
            return "redirect:/admin/category";
        } else
            return "redirect:/admin/category/edit";
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if (this.categoryService.delete(id)) {
            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category";
        }
    }
}

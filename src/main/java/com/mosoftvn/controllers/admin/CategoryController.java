package com.mosoftvn.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
    @GetMapping("/admin/category")
    public String index() {
        return "admin/category/index";
    }

    @GetMapping("/admin/add-category")
    public String add() {
        return "admin/category/add";
    }
}

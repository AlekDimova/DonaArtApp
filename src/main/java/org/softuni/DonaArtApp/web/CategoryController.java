package org.softuni.DonaArtApp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("/categories")
    public String allBrands() {
        return "categories";
    }

}

package com.fixedcode.sociallogindemo.ProductController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public List<String> getAllProducts()
    {
        return Arrays.asList("Samsung", "Apple", "Others");
    }
    @GetMapping("/update")
    public String update()
    {
        return "Only logged-in users are authorised to accessed ";
    }
}

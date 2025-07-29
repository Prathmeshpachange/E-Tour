package controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CategoryDTO;
import service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*") 
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    } 
}

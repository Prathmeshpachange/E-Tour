package controller;

import repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import dto.CategoryDTO;
import dto.SubcategoryDTO;
import entity.Subcategory;
import mapper.Mapper;
import service.CategoryService;
import service.SubcategoryService;

@RestController
@RequestMapping("/api/subcategories")
@CrossOrigin(origins = "*") 
public class SubCategoryController {
	
	@Autowired
	SubcategoryService sub_cat_service;
	
	@GetMapping
	public ResponseEntity<List<SubcategoryDTO>> getAllSubcategories(){
		List<SubcategoryDTO> subCat=sub_cat_service.getAllSubcategories();
		return ResponseEntity.ok(subCat);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<SubcategoryDTO>> getByCategory(@PathVariable Integer categoryId) {
	    List<SubcategoryDTO> list = sub_cat_service.getByCategoryId(categoryId);
	    return ResponseEntity.ok(list);
	}

	
}
package controller;

import repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> getSubcategoryById(@PathVariable int id) {
        SubcategoryDTO subcategory = sub_cat_service.getSubcategoryById(id);
        return ResponseEntity.ok(subcategory);
    }

    @PostMapping
    public ResponseEntity<SubcategoryDTO> createSubcategory(@RequestBody SubcategoryDTO subcategoryDTO) {
        SubcategoryDTO created = sub_cat_service.createSubcategory(subcategoryDTO);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable int id) {
    	sub_cat_service.deleteSubcategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> updateSubcategory(@PathVariable int id, @RequestBody SubcategoryDTO subcategoryDTO) {
        SubcategoryDTO updated = sub_cat_service.updateSubcategory(id, subcategoryDTO);
        return ResponseEntity.ok(updated);
    }
    
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<SubcategoryDTO>> getByCategory(@PathVariable Integer categoryId) {
	    List<SubcategoryDTO> list = sub_cat_service.getByCategoryId(categoryId);
	    return ResponseEntity.ok(list);
	}
}
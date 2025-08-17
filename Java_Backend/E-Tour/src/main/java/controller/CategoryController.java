package controller;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import entity.*;
import mapper.Mapper;
import dto.*;
import repository.CategoryRepository;
import repository.PackageRepository;
import service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*") 
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	 @Autowired
	private CategoryRepository categoryRepository;
	 

	@Autowired
    private PackageRepository tourPackageRepository;
	
	@GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    } 
	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
	    return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
	    CategoryDTO dto = categoryService.getCategoryById(id);
	    return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
	    return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
	    categoryService.deleteCategory(id);
	    return ResponseEntity.ok("Category deleted successfully");
	}

	@GetMapping("/{categoryId}/packages")
    public ResponseEntity<List<PackageDTO>> getPackagesByCategory(@PathVariable Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<TourPackage> packages;

        if (category.getFlag()) {
            // Special category: get packages directly using category_id
            packages = tourPackageRepository.findByCategory(category);
        } else {
            // Normal: get packages through subcategories
            packages = tourPackageRepository.findByCategoryViaSubcategories(category);
        }

        List<PackageDTO> dtos = packages.stream()
                .map(Mapper::MapToPackageDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

	
}

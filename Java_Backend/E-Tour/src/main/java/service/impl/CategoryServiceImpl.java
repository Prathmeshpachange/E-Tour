package service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import mapper.Mapper;
import dto.CategoryDTO;
import entity.Category;
import repository.CategoryRepository;
import service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> getAllCategories() {
		
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();

		for (Category category : categories) {
		    CategoryDTO dto = Mapper.MaptoCategoryDTO(category);
		    categoryDTOs.add(dto);
		}

		return categoryDTOs;

	}
	
	 @Override
	    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
	        Category category = Mapper.MaptoCategory(categoryDTO);
	        Category saved = categoryRepository.save(category);
	        return Mapper.MaptoCategoryDTO(saved);
	    }

	    @Override
	    public CategoryDTO getCategoryById(Integer id) {
	        Optional<Category> optional = categoryRepository.findById(id);
	        return optional.map(Mapper::MaptoCategoryDTO).orElse(null);
	    }

	    @Override
	    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
	        Optional<Category> optional = categoryRepository.findById(id);
	        if (optional.isPresent()) {
	            Category existing = optional.get();
	            existing.setCategoryName(categoryDTO.getCategoryName());
	            existing.setCategoryCode(categoryDTO.getCategoryCode());
	            existing.setCategoryImagePath(categoryDTO.getCategoryImagePath());
	            existing.setFlag(categoryDTO.getFlag());
	            Category updated = categoryRepository.save(existing);
	            return Mapper.MaptoCategoryDTO(updated);
	        }
	        return null;
	    }

	    @Override
	    public void deleteCategory(Integer id) {
	        categoryRepository.deleteById(id);
	    }
	
	
}

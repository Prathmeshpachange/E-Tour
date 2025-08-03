package service;

import java.util.*;


import dto.CategoryDTO;


public interface CategoryService {
	  List<CategoryDTO> getAllCategories();

	CategoryDTO createCategory(CategoryDTO categoryDTO);

	CategoryDTO getCategoryById(Integer id);

	CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);

	void deleteCategory(Integer id);
}

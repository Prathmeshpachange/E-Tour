package service.impl;


import java.util.ArrayList;
import java.util.List;

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
	
	
}

package mapper;

import dto.CategoryDTO;
import entity.Category;

public class Mapper {
	public static CategoryDTO MaptoCategoryDTO(Category category) {
        if (category == null) return null;

        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryCode(category.getCategoryCode());
        dto.setCategoryName(category.getCategoryName());
        dto.setCategoryImagePath(category.getCategoryImagePath());
        dto.setFlag(category.getFlag());
        
        return dto;
}
	
	public static Category MaptoCategory(CategoryDTO dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        category.setCategoryCode(dto.getCategoryCode());
        category.setCategoryName(dto.getCategoryName());
        category.setCategoryImagePath(dto.getCategoryImagePath());
        category.setFlag(dto.getFlag());

        return category;
    }
}
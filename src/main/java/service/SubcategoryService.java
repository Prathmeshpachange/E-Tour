package service;
import dto.SubcategoryDTO;
import java.util.*;

public interface SubcategoryService {
	 List<SubcategoryDTO> getAllSubcategories();
	 SubcategoryDTO getSubcategoryById(int id);
	 SubcategoryDTO createSubcategory(SubcategoryDTO subcategoryDTO);
	 void deleteSubcategory(int id);
	 SubcategoryDTO updateSubcategory(int id, SubcategoryDTO subcategoryDTO);
}

package service;
import dto.SubcategoryDTO;
import java.util.*;

public interface SubcategoryService {
	List<SubcategoryDTO> getAllSubcategories();
	List<SubcategoryDTO> getByCategoryId(Integer categoryId);

}

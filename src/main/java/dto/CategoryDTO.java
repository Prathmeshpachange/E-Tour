package dto;
import java.util.List;

public class CategoryDTO {
	private Integer categoryId;
	private String categoryCode;
	private String categoryName;
	private String categoryImagePath;
	private Boolean flag;
	
	private List<SubcategoryDTO> subcategories;
}

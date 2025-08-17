package dto;
import java.util.List;

import entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO {
	private Integer categoryId;
	private String categoryCode;
	private String categoryName;
	private String categoryImagePath;
	private Boolean flag;
	
	private List<SubcategoryDTO> subcategories;
}

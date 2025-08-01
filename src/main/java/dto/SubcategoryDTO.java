package dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryDTO {
	 	private Integer subcategoryId;
	    private String subcategoryName;
	    private String subcategoryImagePath;
	    private Boolean flag;
	    private Integer categoryId; // avoid nesting whole Category entity
}

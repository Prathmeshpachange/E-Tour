package dto;

public class SubcategoryDTO {
	 	private Integer subcategoryId;
	    private String subcategoryName;
	    private String subcategoryImagePath;
	    private Boolean flag;
	    private Integer categoryId; // avoid nesting whole Category entity
}

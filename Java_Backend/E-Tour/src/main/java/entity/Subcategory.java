package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subcategory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subcategoryId;
    private String subcategoryName;
    private String subcategoryImagePath;
    private Boolean flag;
    
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private Category category;

}

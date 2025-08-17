package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Subcategory;
import java.util.*;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
	List<Subcategory> findByCategory_CategoryId(Integer categoryId);


}

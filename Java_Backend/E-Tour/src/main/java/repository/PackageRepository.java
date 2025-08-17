package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import entity.*;
import java.util.*;

@Repository
public interface PackageRepository extends JpaRepository<TourPackage, Integer> {
	List<TourPackage> findBySubcategorySubcategoryId(Integer subcategoryId);
	
	  List<TourPackage> findByCategory(Category category);
	
	@Query("SELECT p FROM TourPackage p WHERE p.category.categoryId = :categoryId AND p.subcategory IS NULL")
	List<TourPackage> findAllBySpecialCategory(@Param("categoryId") Integer categoryId);
	
	 @Query("SELECT p FROM TourPackage p WHERE p.subcategory.category = :category")
	    List<TourPackage> findByCategoryViaSubcategories(@Param("category") Category category);


}

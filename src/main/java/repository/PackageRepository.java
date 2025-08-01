package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entity.TourPackage;

@Repository
public interface PackageRepository extends JpaRepository<TourPackage, Integer> {
}

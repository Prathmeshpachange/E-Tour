package repository;

import entity.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostRepository extends JpaRepository<Cost, Integer> {
    Optional<Cost> findByTourPackage_PackageId(Integer packageId);
}

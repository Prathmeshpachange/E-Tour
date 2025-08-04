package service;

import java.util.List;
import dto.PackageDTO;

public interface PackageService {
    List<PackageDTO> getAllPackages();
    List<PackageDTO> getPackagesBySubcategory(Integer subcategoryId);
    PackageDTO getPackageById(Integer id);
}

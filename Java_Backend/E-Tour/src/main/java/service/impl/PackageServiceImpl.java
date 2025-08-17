package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.PackageDTO;
import entity.TourPackage;
import mapper.Mapper;
import repository.PackageRepository;
import service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public List<PackageDTO> getAllPackages() {
        List<TourPackage> packages = packageRepository.findAll();
        List<PackageDTO> packageDTOs = new ArrayList<>();

        for (TourPackage pkg : packages) {
            PackageDTO dto = Mapper.MapToPackageDTO(pkg);
            packageDTOs.add(dto);
        }

        return packageDTOs;
    }
    
    @Override
    public PackageDTO getPackageById(Integer id) {
        TourPackage tourPackage = packageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Package not found"));
        return Mapper.MapToPackageDTO(tourPackage);
    }

    
    @Override
    public List<PackageDTO> getPackagesBySubcategory(Integer subcategoryId) {
        List<TourPackage> packages = packageRepository.findBySubcategorySubcategoryId(subcategoryId);
        return packages.stream()
                       .map(Mapper::MapToPackageDTO)  
                       .collect(Collectors.toList());
    }


}

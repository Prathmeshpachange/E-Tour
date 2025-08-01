package service.impl;

import java.util.ArrayList;
import java.util.List;

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
}

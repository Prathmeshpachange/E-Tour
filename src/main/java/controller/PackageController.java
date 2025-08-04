package controller;

import java.util.List;
import java.util.stream.Collectors;

import dto.PackageDTO;
import entity.TourPackage;
import entity.Subcategory;
import mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import repository.PackageRepository;
import repository.SubcategoryRepository;
import service.PackageService;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin(origins = "*")
public class PackageController {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;
    
    @Autowired
    private PackageService packService;

    @GetMapping
    public ResponseEntity<List<PackageDTO>> getAllPackages() {
        List<TourPackage> tourPackages = packageRepository.findAll();
        List<PackageDTO> dtos = tourPackages.stream()
                                        .map(Mapper::MapToPackageDTO)
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    
    @GetMapping("/subcategory/{subcategoryId}")
    public ResponseEntity<List<PackageDTO>> getPackagesBySubcategory(@PathVariable Integer subcategoryId) {
        List<PackageDTO> filtered = packService.getPackagesBySubcategory(subcategoryId);
        return ResponseEntity.ok(filtered);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}")
    public ResponseEntity<PackageDTO> getPackageById(@PathVariable Integer id) {
        PackageDTO dto = packService.getPackageById(id);
        return ResponseEntity.ok(dto);
    }

  
    @PostMapping
    public ResponseEntity<PackageDTO> addPackage(@RequestBody PackageDTO dto) {
        Subcategory subcategory = subcategoryRepository.findById(dto.getSubcategoryId())
                                                       .orElse(null);
        if (subcategory == null) {
            return ResponseEntity.badRequest().build();
        }

        TourPackage pkg = Mapper.MapToPackage(dto, subcategory);
        TourPackage saved = packageRepository.save(pkg);
        return ResponseEntity.ok(Mapper.MapToPackageDTO(saved));
    }
}

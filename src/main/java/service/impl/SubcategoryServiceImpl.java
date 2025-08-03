package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.SubcategoryDTO;
import entity.Category;
import entity.Subcategory;
import mapper.Mapper;
import repository.SubcategoryRepository;
import service.SubcategoryService;

@Service
public class SubcategoryServiceImpl implements SubcategoryService{

	@Autowired
	private SubcategoryRepository subCatRepo;
	@Override
	public List<SubcategoryDTO> getAllSubcategories() {
	 	List<Subcategory> subcategories=subCatRepo.findAll();
	 	List<SubcategoryDTO> subCatDto = new ArrayList<>();
	 	for(Subcategory subcategory:subcategories) {
	 		SubcategoryDTO subDto = Mapper.MaptoSubcategoryDto(subcategory);
	 		subCatDto.add(subDto);
	 	}
		return subCatDto;
	}
 
	 @Override
	    public SubcategoryDTO getSubcategoryById(int id) {
	        Optional<Subcategory> optional = subCatRepo.findById(id);
	        return optional.map(Mapper::MaptoSubcategoryDto).orElse(null);
	    }

	    @Override
	    public SubcategoryDTO createSubcategory(SubcategoryDTO subcategoryDTO) {
	        Subcategory subcategory = Mapper.MaptoSubcategory(subcategoryDTO);
	        Subcategory saved = subCatRepo.save(subcategory);
	        return Mapper.MaptoSubcategoryDto(saved);
	    }

	    @Override
	    public void deleteSubcategory(int id) {
	        subCatRepo.deleteById(id);
	    }

	    @Override
	    public SubcategoryDTO updateSubcategory(int id, SubcategoryDTO subcategoryDTO) {
	        Optional<Subcategory> optional = subCatRepo.findById(id);
	        if (optional.isPresent()) {
	            Subcategory existing = optional.get();
	            existing.setSubcategoryName(subcategoryDTO.getSubcategoryName());
	            existing.setSubcategoryImagePath(subcategoryDTO.getSubcategoryImagePath());
	            existing.setFlag(subcategoryDTO.getFlag());
	            Subcategory updated = subCatRepo.save(existing);
	            return Mapper.MaptoSubcategoryDto(updated);
	        }
	        return null;
	    }
}

package service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<SubcategoryDTO> getByCategoryId(Integer categoryId) {
	    List<Subcategory> subcategories = subCatRepo.findByCategory_CategoryId(categoryId);
	    return subcategories.stream().map(Mapper::MaptoSubcategoryDto).toList();
	}


}

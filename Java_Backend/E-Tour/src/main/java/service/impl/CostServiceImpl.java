package service.impl;

import dto.CostDTO;
import entity.Cost;
import mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CostRepository;
import service.CostService;

@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostRepository costRepository;

    @Override
    public CostDTO getCostByPackageId(Integer packageId) {
        Cost cost = costRepository.findByTourPackage_PackageId(packageId).orElse(null);
        return cost != null ? Mapper.mapToCostDTO(cost) : null;
    }

}

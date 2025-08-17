package controller;

import dto.CostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CostService;

@RestController
@RequestMapping("/api/costs")
@CrossOrigin(origins = "*") // Allow React frontend
public class CostController {

    @Autowired
    private CostService costService;

    @GetMapping("/package/{packageId}")
    public ResponseEntity<CostDTO> getCostByPackageId(@PathVariable Integer packageId) {
        CostDTO cost = costService.getCostByPackageId(packageId);
        return cost != null ? ResponseEntity.ok(cost) : ResponseEntity.notFound().build();
    }
}


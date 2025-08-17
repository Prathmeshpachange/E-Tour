	package controller;
	
	import dto.PassengerDTO;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;
	import service.PassengerService;
	
	import java.util.List;
	
	@RestController
	@RequestMapping("/api/passengers")
	public class PassengerController {
	
	    @Autowired
	    private PassengerService passengerService;
	
	    @PostMapping
	    public PassengerDTO savePassenger(@RequestBody PassengerDTO dto) {
	        return passengerService.savePassenger(dto);
	    }
	
	    @GetMapping("/{id}")
	    public PassengerDTO getPassenger(@PathVariable Integer id) {
	        return passengerService.getPassengerById(id);
	    }
	
	    @GetMapping
	    public List<PassengerDTO> getAll() {
	        return passengerService.getAllPassengers();
	    }
	
	    @PutMapping("/{id}")
	    public PassengerDTO updatePassenger(@PathVariable Integer id, @RequestBody PassengerDTO dto) {
	        return passengerService.updatePassenger(id, dto);
	    }
	
	    @DeleteMapping("/{id}")
	    public void deletePassenger(@PathVariable Integer id) {
	        passengerService.deletePassenger(id);
	    }
	}

/**
 * 
 */
package za.co.mafeka.owner_rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import za.co.mafeka.owner_rest.dto.OwnerRequest;
import za.co.mafeka.owner_rest.dto.OwnerResponse;
import za.co.mafeka.owner_rest.service.OwnerService;

/**
 * 
 */
@Controller
@RequestMapping("/api/v1/owners")
public class OwnerController {
	private final OwnerService ownerService;
	
	/**
	 * @param ownerService
	 */
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@GetMapping("{id}")
	public ResponseEntity<OwnerResponse> getOwner(@PathVariable Long id) {
		return ResponseEntity.ok(ownerService.getOwner(id));
	}
	
	@PostMapping
	public ResponseEntity<OwnerResponse> createCar(@RequestBody OwnerRequest owner) {
		return ResponseEntity.ok(ownerService.createOwner(owner));
	}
	
	@GetMapping()
	public ResponseEntity<List<OwnerResponse>> getAllOwners() {
		return ResponseEntity.ok(ownerService.getAllOwners());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OwnerResponse> updateCar(@PathVariable Long id, @RequestBody OwnerRequest owner) {
		return ResponseEntity.ok(ownerService.updateOwner(id, owner));
	}
	
	@DeleteMapping("/{id}")
	public String deleteCar(@PathVariable Long id) {
		ownerService.deleteOwner(id);
		return "Successfuly deleted Car with id=" + id;
	}
}

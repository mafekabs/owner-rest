/**
 * 
 */
package za.co.mafeka.owner_rest.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import za.co.mafeka.owner_rest.dto.OwnerRequest;
import za.co.mafeka.owner_rest.dto.OwnerResponse;
import za.co.mafeka.owner_rest.model.Owner;
import za.co.mafeka.owner_rest.repository.OwnerRepository;
import za.co.mafeka.owner_rest.service.OwnerService;

/**
 * 
 */
@Service
public class OwnerServiceImpl implements OwnerService {
	private final OwnerRepository ownerRepository;
	
	/**
	 * @param ownerService
	 */
	public OwnerServiceImpl(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public OwnerResponse createOwner(OwnerRequest owner) {
		Owner savedOwner = ownerRepository.save(covertRequestToOwner(owner));
		return covertOwnerToResponse(savedOwner);
	}

	@Override
	public OwnerResponse getOwner(Long id) {
		Optional<Owner> owner = ownerRepository.findById(id);
		if(owner.isEmpty()) {
			throw new EntityNotFoundException(String.format("No Owner found for ID = %s", id));
		}
		return covertOwnerToResponse(owner.get());
	}

	@Override
	public List<OwnerResponse> getAllOwners() {
		List<Owner> owners = ownerRepository.findAll();
		List<OwnerResponse> ownersRes = owners.stream()
				.map(owner -> covertOwnerToResponse(owner))
				.collect(Collectors.toList());
		return ownersRes;
	}

	@Override
	public OwnerResponse updateOwner(Long id, OwnerRequest owner) {
		OwnerResponse ownerRes = getOwner(id);

		Owner savedOwner = ownerRepository.save(covertOwnerResponseToOwner(ownerRes));
		return covertOwnerToResponse(savedOwner);
	}

	@Override
	public String deleteOwner(Long id) {
		ownerRepository.deleteById(id);
		return String.format("Successfully deleted an Owner with ID = %s", id);
	}
	
	public Owner covertRequestToOwner(OwnerRequest owner) {
		Owner _owner = Owner.builder()
				  .firstName(owner.firstname())
				  .lastName(owner.lastname())
				  .build();
		return _owner;
	}
	
	public Owner covertOwnerResponseToOwner(OwnerResponse ownerResponse) {
		Owner _owner = Owner.builder()
				  .id(ownerResponse.id())
				  .firstName(ownerResponse.firstname())
				  .lastName(ownerResponse.lastName())
				  .build();
		return _owner;
	}
	
	public OwnerResponse covertOwnerToResponse(Owner owner) {
		OwnerResponse response =  OwnerResponse.builder()
		.id(owner.getId())
		.firstname(owner.getFirstName())
		.lastName(owner.getLastName())
		.build();
		return response;
	}

}

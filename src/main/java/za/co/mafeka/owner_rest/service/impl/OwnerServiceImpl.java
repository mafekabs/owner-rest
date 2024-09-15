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
		Owner _owner = Owner.builder()
			  .firstName(owner.firstname())
			  .lastName(owner.lastname())
			  .build();
		Owner savedOwner = ownerRepository.save(_owner);
		return OwnerResponse.builder()
				.id(savedOwner.getId())
				.firstname(savedOwner.getFirstName())
				.lastName(savedOwner.getLastName())
				.build();
	}

	@Override
	public OwnerResponse getOwner(Long id) {
		Optional<Owner> owner = ownerRepository.findById(id);
		if(owner.isEmpty()) {
			throw new EntityNotFoundException(String.format("No Owner found for ID = %s", id));
		}
		return OwnerResponse.builder()
				.id(owner.get().getId())
				.firstname(owner.get().getFirstName())
				.lastName(owner.get().getLastName())
				.build();
	}

	@Override
	public List<OwnerResponse> getAllOwners() {
		List<Owner> owners = ownerRepository.findAll();
		List<OwnerResponse> ownersRes = owners.stream()
				.map(owner -> new OwnerResponse(owner.getId(), owner.getFirstName(), owner.getLastName()))
				.collect(Collectors.toList());
		return ownersRes;
	}

	@Override
	public OwnerResponse updateOwner(Long id, OwnerRequest owner) {
		OwnerResponse ownerRes = getOwner(id);
		Owner _owner = Owner.builder()
			  .id(ownerRes.id())
			  .firstName(ownerRes.firstname())
			  .lastName(ownerRes.lastName())
			  .build();
		Owner savedOwner = ownerRepository.save(_owner);
		return OwnerResponse.builder()
			   .id(savedOwner.getId())
			   .firstname(savedOwner.getFirstName())
			   .lastName(savedOwner.getLastName())
			   .build();
	}

	@Override
	public String deleteOwner(Long id) {
		ownerRepository.deleteById(id);
		return String.format("Successfully deleted an Owner with ID = %s", id);
	}

}

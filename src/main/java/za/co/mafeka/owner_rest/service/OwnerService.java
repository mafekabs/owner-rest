package za.co.mafeka.owner_rest.service;

import java.util.List;

import za.co.mafeka.owner_rest.dto.OwnerRequest;
import za.co.mafeka.owner_rest.dto.OwnerResponse;

public interface OwnerService {
	OwnerResponse createOwner(OwnerRequest owner);
	OwnerResponse getOwner(Long id);
	List<OwnerResponse> getAllOwners();
	OwnerResponse updateOwner(Long id, OwnerRequest owner);
	String deleteOwner(Long id);
}

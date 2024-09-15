package za.co.mafeka.owner_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.mafeka.owner_rest.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}

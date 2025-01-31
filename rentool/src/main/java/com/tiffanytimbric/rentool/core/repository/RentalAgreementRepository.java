package com.tiffanytimbric.rentool.core.repository;

import com.tiffanytimbric.rentool.core.model.RentalAgreement;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
//@PreAuthorize("hasRole('USER')")
public interface RentalAgreementRepository extends ListCrudRepository<RentalAgreement, UUID> {

    @NonNull
    List<RentalAgreement> findByToolId(String toolId);

    @NonNull
    List<RentalAgreement> findByRenterId(String renterId);

}

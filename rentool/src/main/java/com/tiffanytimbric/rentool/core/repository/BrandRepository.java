package com.tiffanytimbric.rentool.core.repository;

import com.tiffanytimbric.rentool.core.model.Brand;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@PreAuthorize("hasRole('USER')")
public interface BrandRepository extends ListCrudRepository<Brand, String> {
}

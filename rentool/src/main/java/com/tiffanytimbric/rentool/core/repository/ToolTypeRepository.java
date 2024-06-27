package com.tiffanytimbric.rentool.core.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
@PreAuthorize("hasRole('USER')")
public interface ToolTypeRepository extends ListCrudRepository<com.tiffanytimbric.rentool.core.model.ToolType, String> {
}

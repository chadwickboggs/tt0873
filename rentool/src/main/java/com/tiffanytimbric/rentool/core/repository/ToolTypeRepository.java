package com.tiffanytimbric.rentool.core.repository;

import com.tiffanytimbric.rentool.core.model.ToolType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@PreAuthorize("hasRole('USER')")
public interface ToolTypeRepository extends ListCrudRepository<ToolType, String> {
}

package com.tiffanytimbric.rentool.core.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@PreAuthorize("hasRole('USER')")
public interface ToolRepository extends ListCrudRepository<com.tiffanytimbric.rentool.core.model.Tool, UUID> {

    @Nullable
    List<com.tiffanytimbric.rentool.core.model.Tool> findByCode(String code);

}

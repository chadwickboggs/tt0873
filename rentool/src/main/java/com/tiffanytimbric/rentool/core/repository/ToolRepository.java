package com.tiffanytimbric.rentool.core.repository;

import com.tiffanytimbric.rentool.core.model.Tool;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
//@PreAuthorize("hasRole('USER')")
public interface ToolRepository extends ListCrudRepository<Tool, UUID> {

    @NonNull
    Optional<Tool> findByCode(String code);

    @Nullable
    List<Tool> findByType(String type);

    @Nullable
    List<Tool> findByBrand(String type);

}

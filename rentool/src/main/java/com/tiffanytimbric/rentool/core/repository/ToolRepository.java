package com.tiffanytimbric.rentool.core.repository;

import com.tiffanytimbric.rentool.core.model.Tool;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
//@PreAuthorize("hasRole('USER')")
public interface ToolRepository extends ListCrudRepository<Tool, UUID> {

    @Nullable
    List<Tool> findByCode(String code);

    @Nullable
    List<Tool> findByType(String type);

}

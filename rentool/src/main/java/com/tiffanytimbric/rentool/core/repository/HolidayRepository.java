package com.tiffanytimbric.rentool.core.repository;

import com.tiffanytimbric.rentool.core.model.Holiday;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
//@PreAuthorize("hasRole('USER')")
public interface HolidayRepository extends ListCrudRepository<Holiday, LocalDate> {

    @NonNull
    List<Holiday> findByName(String name);

}

package com.tiffanytimbric.rentool.core.controller;

import com.tiffanytimbric.rentool.core.model.Holiday;
import com.tiffanytimbric.rentool.core.repository.HolidayRepository;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
//@PreAuthorize("hasRole('USER')")
public class HolidayController {

    private final HolidayRepository holidayRepository;

    public HolidayController(
            @NonNull final HolidayRepository holidayRepository
    ) {
        this.holidayRepository = holidayRepository;
    }

    @GetMapping("/holidayExist/{date}")
    @NonNull
    public boolean doesExist(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")
            @Nullable final LocalDate date
    ) {
        if (date == null) {
            return holidayRepository.count() > 0;
        }

        return holidayRepository.existsById(date);
    }

    @GetMapping("/holiday")
    @NonNull
    public ResponseEntity<List<Holiday>> readAllHolidays() {
        return ResponseEntity.ofNullable(
                holidayRepository.findAll()
        );
    }

    @GetMapping("/holiday/{dayDate}")
    @NonNull
    public ResponseEntity<Holiday> readHoliday(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")
            @Nullable final LocalDate dayDate
    ) {
        if (dayDate == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                holidayRepository.findById(dayDate)
        );
    }

    @GetMapping("/holidayByName/{name}")
    @NonNull
    public ResponseEntity<List<Holiday>> readHolidayByName(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.ofNullable(
                holidayRepository.findByName(name)
        );
    }

    @PostMapping("/holiday")
    @NonNull
    public ResponseEntity<Holiday> createHoliday(
            @RequestBody @Valid @Nullable final Holiday holiday
    ) {
        if (holiday == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final Holiday savedHoliday = holidayRepository.save(holiday);

        return ResponseEntity.of(
                Optional.of(savedHoliday)
        );
    }

    @PutMapping("/holiday")
    @NonNull
    public ResponseEntity<Holiday> updateHoliday(
            @RequestBody @Valid @Nullable final Holiday holiday
    ) {
        if (holiday == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final Holiday savedHoliday = holidayRepository.save(holiday);

        return ResponseEntity.of(
                Optional.of(savedHoliday)
        );
    }

    @PatchMapping("/holiday")
    @NonNull
    public ResponseEntity<Holiday> patchHoliday(
            @RequestBody @Valid @Nullable final Holiday holiday
    ) {
        if (holiday == null) {
            return ResponseEntity.of(Optional.empty());
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Invalid method, method not implemented.  Method Name: \"patchHoliday\""
        );
    }

    @DeleteMapping("/holiday/{name}")
    @NonNull
    public ResponseEntity<Holiday> deleteHoliday(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")
            @Nullable final LocalDate name
    ) {
        if (name == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<Holiday> holidayOpt = holidayRepository.findById(name);
        if (holidayOpt.isEmpty()) {
            ResponseEntity.of(Optional.empty());
        }

        holidayRepository.deleteById(name);

        return ResponseEntity.of(holidayOpt);
    }

}

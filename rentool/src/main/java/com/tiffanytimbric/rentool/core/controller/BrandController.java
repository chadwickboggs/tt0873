package com.tiffanytimbric.rentool.core.controller;

import com.tiffanytimbric.rentool.core.model.Brand;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
//@PreAuthorize("hasRole('USER')")
public class BrandController {

    private final com.tiffanytimbric.rentool.core.repository.BrandRepository brandRepository;

    public BrandController(
            @NonNull final com.tiffanytimbric.rentool.core.repository.BrandRepository brandRepository
    ) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/brandExist/{name}")
    @NonNull
    public boolean doesExist(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return brandRepository.count() > 0;
        }

        return brandRepository.existsById(name);
    }

    @GetMapping("/brand")
    @NonNull
    public ResponseEntity<List<Brand>> readAllBrands() {
        return ResponseEntity.ofNullable(
                brandRepository.findAll()
        );
    }

    @GetMapping("/brand/{name}")
    @NonNull
    public ResponseEntity<Brand> readBrand(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                brandRepository.findById(name)
        );
    }

    @PostMapping("/brand")
    @NonNull
    public ResponseEntity<Brand> createBrand(
            @RequestBody @Nullable final Brand brand
    ) {
        if (brand == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                Optional.of(brandRepository.save(brand))
        );
    }

    @PutMapping("/brand")
    @NonNull
    public ResponseEntity<Brand> updateBrand(
            @RequestBody @Nullable final Brand brand
    ) {
        if (brand == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                Optional.of(brandRepository.save(brand))
        );
    }

    @PatchMapping("/brand")
    @NonNull
    public ResponseEntity<Brand> patchBrand(
            @RequestBody @Nullable final Brand brand
    ) {
        if (brand == null) {
            return ResponseEntity.of(Optional.empty());
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Invalid method, method not implemented.  Method Name: \"patchBrand\""
        );
    }

    @DeleteMapping("/brand/{name}")
    @NonNull
    public ResponseEntity<Brand> deleteBrand(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<Brand> brandOpt = brandRepository.findById(name);
        if (brandOpt.isEmpty()) {
            ResponseEntity.of(Optional.empty());
        }

        brandRepository.deleteById(name);

        return ResponseEntity.of(brandOpt);
    }

}

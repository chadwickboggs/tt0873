package com.tiffanytimbric.rentool.core.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@PreAuthorize("hasRole('USER')")
public class ToolTypeController {

    private final com.tiffanytimbric.rentool.core.repository.ToolTypeRepository toolTypeRepository;

    public ToolTypeController(
            @NonNull final com.tiffanytimbric.rentool.core.repository.ToolTypeRepository toolTypeRepository
    ) {
        this.toolTypeRepository = toolTypeRepository;
    }

    @GetMapping("/toolTypeExist/{name}")
    @NonNull
    public boolean doesExist(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return toolTypeRepository.count() > 0;
        }

        return toolTypeRepository.existsById(name);
    }

    @GetMapping("/toolType/{name}")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.ToolType> readToolType(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                toolTypeRepository.findById(name)
        );
    }

    @PostMapping("/toolType")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.ToolType> createToolType(
            @RequestBody @Nullable final com.tiffanytimbric.rentool.core.model.ToolType toolType
    ) {
        if (toolType == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                Optional.of(toolTypeRepository.save(toolType))
        );
    }

    @PutMapping("/toolType")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.ToolType> updateToolType(
            @RequestBody @Nullable final com.tiffanytimbric.rentool.core.model.ToolType toolType
    ) {
        if (toolType == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                Optional.of(toolTypeRepository.save(toolType))
        );
    }

    @PatchMapping("/toolType")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.ToolType> patchToolType(
            @RequestBody @Nullable final com.tiffanytimbric.rentool.core.model.ToolType toolType
    ) {
        if (toolType == null) {
            return ResponseEntity.of(Optional.empty());
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Invalid method, method not implemented.  Method Name: \"patchBrand\""
        );
    }

    @DeleteMapping("/toolType/{name}")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.ToolType> deleteToolType(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<com.tiffanytimbric.rentool.core.model.ToolType> toolTypeOpt = toolTypeRepository.findById(name);
        if (toolTypeOpt.isEmpty()) {
            ResponseEntity.of(Optional.empty());
        }

        toolTypeRepository.deleteById(name);

        return ResponseEntity.of(toolTypeOpt);
    }

}

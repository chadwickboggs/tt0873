package com.tiffanytimbric.rentool.core.controller;

import com.tiffanytimbric.rentool.core.model.ToolType;
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

    @GetMapping("/toolType")
    @NonNull
    public ResponseEntity<List<ToolType>> readAllToolTypes() {
        return ResponseEntity.ofNullable(
                toolTypeRepository.findAll()
        );
    }

    @GetMapping("/toolType/{name}")
    @NonNull
    public ResponseEntity<ToolType> readToolType(
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
    public ResponseEntity<ToolType> createToolType(
            @RequestBody @Nullable final ToolType toolType
    ) {
        if (toolType == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final ToolType savedToolType = toolTypeRepository.save(toolType);

        return ResponseEntity.of(
                Optional.of(savedToolType)
        );
    }

    @PutMapping("/toolType")
    @NonNull
    public ResponseEntity<ToolType> updateToolType(
            @RequestBody @Nullable final ToolType toolType
    ) {
        if (toolType == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final ToolType savedToolType = toolTypeRepository.save(toolType);

        return ResponseEntity.of(
                Optional.of(savedToolType)
        );
    }

    @PatchMapping("/toolType")
    @NonNull
    public ResponseEntity<ToolType> patchToolType(
            @RequestBody @Nullable final ToolType toolType
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
    public ResponseEntity<ToolType> deleteToolType(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<ToolType> toolTypeOpt = toolTypeRepository.findById(name);
        if (toolTypeOpt.isEmpty()) {
            ResponseEntity.of(Optional.empty());
        }

        toolTypeRepository.deleteById(name);

        return ResponseEntity.of(toolTypeOpt);
    }

}

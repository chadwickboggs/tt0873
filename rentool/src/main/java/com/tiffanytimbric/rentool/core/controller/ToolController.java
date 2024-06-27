package com.tiffanytimbric.rentool.core.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@PreAuthorize("hasRole('USER')")
public class ToolController {

    private final com.tiffanytimbric.rentool.core.repository.ToolRepository toolRepository;
    private final UserController userController;

    public ToolController(
            @NonNull final com.tiffanytimbric.rentool.core.repository.ToolRepository toolRepository,
            @NonNull final UserController userController
    ) {
        this.toolRepository = toolRepository;
        this.userController = userController;
    }

    @GetMapping("/toolExist/{id}")
    @NonNull
    public boolean doesExist(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return toolRepository.count() > 0;
        }

        return toolRepository.existsById(id);
    }

    @GetMapping("/tool/{id}")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.Tool> readTool(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                toolRepository.findById(id)
        );
    }

    @GetMapping("/toolByName/{name}")
    @NonNull
    public ResponseEntity<List<com.tiffanytimbric.rentool.core.model.Tool>> readToolByName(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.ofNullable(
                toolRepository.findByCode(name)
        );
    }

    @PostMapping("/tool")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.Tool> createTool(
            @RequestBody @Nullable final com.tiffanytimbric.rentool.core.model.Tool tool
    ) {
        if (tool == null) {
            return ResponseEntity.of(Optional.empty());
        }

        if (tool.idOpt().isEmpty()) {
            tool.setId(UUID.randomUUID());
        }

        return ResponseEntity.of(
                Optional.of(toolRepository.save(tool))
        );
    }

    @PutMapping("/tool")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.Tool> updateTool(
            @RequestBody @Nullable final com.tiffanytimbric.rentool.core.model.Tool tool
    ) {
        if (tool == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                Optional.of(toolRepository.save(tool))
        );
    }

    @PatchMapping("/tool")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.Tool> patchTool(
            @RequestBody @Nullable final com.tiffanytimbric.rentool.core.model.Tool tool
    ) {
        if (tool == null) {
            return ResponseEntity.of(Optional.empty());
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Invalid method, method not implemented.  Method Name: \"patchTool\""
        );
    }

    @DeleteMapping("/tool/{id}")
    @NonNull
    public ResponseEntity<com.tiffanytimbric.rentool.core.model.Tool> deleteTool(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<com.tiffanytimbric.rentool.core.model.Tool> toolOpt = toolRepository.findById(id);
        if (toolOpt.isEmpty()) {
            return ResponseEntity.of(Optional.empty());
        }

        toolRepository.deleteById(id);

        return ResponseEntity.of(toolOpt);
    }

}

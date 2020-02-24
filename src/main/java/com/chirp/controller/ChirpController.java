package com.chirp.controller;

import com.chirp.exception.ChirpNotFoundException;
import com.chirp.model.Chirp;
import com.chirp.repository.ChirpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("/api")
public class ChirpController {

    @Autowired
    private ChirpRepository chirpRepository;

    @GetMapping("/chirps")
    public ResponseEntity<List<Chirp>> getAll() {
        log.info("Getting all chirps...");

        return ResponseEntity.ok(
                StreamSupport.stream(
                        chirpRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/chirp/{id}")
    public ResponseEntity<Chirp> get(@PathVariable String id)
            throws ChirpNotFoundException {
        log.info("Getting chirp with id={}", id);

        return ResponseEntity.ok(
                chirpRepository.findById(id)
                        .orElseThrow(ChirpNotFoundException::new)
        );
    }

    @PostMapping("/chirp")
    public ResponseEntity<Chirp> add(
            @RequestBody @Valid Chirp chirp)
            throws ChirpNotFoundException {
        log.info("Adding chirp...");

        chirp.setLastUpdate(LocalDateTime.now());

        Chirp created = chirpRepository.save(chirp);

        log.info("Added Chirp with id={}", created.getId());

        return ResponseEntity.ok(created);
    }

    @PutMapping("/chirp/{uuid}")
    public ResponseEntity<Chirp> update(
            @PathVariable String id,
            @RequestBody @Valid Chirp chirp)
            throws ChirpNotFoundException {
        log.info("Updating chirp with id={}", id);

        Chirp updated =
                chirpRepository.findById(id)
                        .orElseThrow(ChirpNotFoundException::new);

        updated.setComment(chirp.getComment());
        updated.setLastUpdate(LocalDateTime.now());

        chirpRepository.save(updated);

        log.info("Updated Chirp with id={}", id);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/chirp/{id}")
    public ResponseEntity delete(
            @PathVariable String id,
            @RequestBody @Valid Chirp chirp)
            throws ChirpNotFoundException {
        log.info("Deleting Chirp with id={}", id);

        chirpRepository.findById(id)
                .orElseThrow(ChirpNotFoundException::new);

        chirpRepository.delete(chirp);

        log.info("Deleted Chirp with id={}", id);

        return ResponseEntity.noContent().build();
    }

}

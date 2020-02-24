package com.chirp.repository;

import com.chirp.model.Chirp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChirpRepository extends CrudRepository<Chirp, String> {
}

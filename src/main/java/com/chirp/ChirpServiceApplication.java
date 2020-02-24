package com.chirp;

import com.chirp.model.Chirp;
import com.chirp.repository.ChirpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@SpringBootApplication
public class ChirpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChirpServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ChirpRepository chirpRepository) {
		return (args) -> {
			List<Chirp> chirps =
					Collections.singletonList(
							Chirp.builder()
									.id("dsfsd")
									.author("Joe Bloggs")
									.comment("My first chirp!")
									.lastUpdate(LocalDateTime.now())
									.build()
					);

			StreamSupport.stream(
					chirpRepository.saveAll(chirps).spliterator(), false)
					.collect(Collectors.toList()
					).forEach(saved -> log.info("Saving chirp {}", saved));
		};
	}

}

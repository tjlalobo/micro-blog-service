package com.chirp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Chirp {

    @Id
    @JsonProperty("id")
    @NotBlank
    private String id;

    @JsonProperty("author")
    @NotBlank
    private String author;

    @JsonProperty("comment")
    @NotBlank
    private String comment;

    @JsonProperty("lastUpdate")
    private LocalDateTime lastUpdate;

}

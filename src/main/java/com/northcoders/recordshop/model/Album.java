package com.northcoders.recordshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Album {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String albumName;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    Artist artist;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate releaseDate;

    @Column
    Genre genre;

    @Column
    Format format;

    @Column
    Long stockQuantity;

    @Column
    Double price;

}

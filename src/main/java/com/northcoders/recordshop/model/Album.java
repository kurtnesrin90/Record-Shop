package com.northcoders.recordshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String albumName;

    @Column
    String artist;

    @Column
    LocalDate releaseDate;

    @Column
    Genre genre;

    @Column
    Format format;

    @Column
    Long StockQuantity;

    @Column
    Double price;



}

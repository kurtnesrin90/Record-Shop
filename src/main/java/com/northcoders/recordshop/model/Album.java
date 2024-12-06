package com.northcoders.recordshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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

 //   @ManyToOne
  //  @JoinColumn(name = "artist_id")
    @Column
    String artist;

    @Column
   // @JsonFormat(pattern="yyyy-MM-dd")
    int releaseYear;

    @Column
    Genre genre;

    @Column
    Format format;

    @Column
    Long stockQuantity;

    @Column
    Double price;

}

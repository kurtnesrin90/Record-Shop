package com.northcoders.recordshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @OneToMany(mappedBy = "artist" , cascade = CascadeType.ALL)
    List<Album> albums;

    public Artist(String name) {
    }
}
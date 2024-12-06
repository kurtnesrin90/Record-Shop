package com.northcoders.recordshop.controller;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.model.Format;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.service.RecordShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordShopControllerTests {

    @Mock
    RecordShopService recordShopService;

    @InjectMocks
    RecordShopController recordShopController;

    @Autowired
    MockMvc mockMvcController;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(recordShopController).build();
    }

    @Test
    @DisplayName("Tests getAllAlbums method to get all the albums from database")
    void test_GetAllAlbums() throws Exception {

        //Arrange
        List<Album> albumLists = new ArrayList<>();

        Album album1 = Album.builder()
                .id(1L)
                .albumName("The Tortured Poets Department: The Anthology")
                .artist(new Artist("Taylor Swift"))
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseDate(LocalDate.parse("2024-05-05"))
                .stockQuantity(50L)
                .build();
        albumLists.add(album1);

        Album album2 = Album.builder()
                .id(2L)
                .albumName("B-Sides And Otherwise")
                .artist(new Artist("Morphine"))
                .genre(Genre.ROCK)
                .format(Format.VINYL)
                .price(11.95)
                .releaseDate(LocalDate.parse("2024-01-01"))
                .stockQuantity(98L)
                .build();
        albumLists.add(album2);

        //Act
        when(this.recordShopService.getAllAlbums()).thenReturn(albumLists);

        //Assert
       this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(album1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].albumName").value(album1.getAlbumName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value(album1.getArtist()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value(album1.getGenre().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].format").value(album1.getFormat().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(album1.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseDate").value(album1.getReleaseDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stockQuantity").value(album1.getStockQuantity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(album2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].albumName").value(album2.getAlbumName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value(album2.getArtist()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genre").value(album2.getGenre().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].format").value(album2.getFormat().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(album2.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseDate").value(album2.getReleaseDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stockQuantity").value(album2.getStockQuantity()));
    }

    @Test
    @DisplayName("Tests getAlbumsById method to get the album that has the Id")
    void test_GetAlbumById() throws Exception {

        //Arrange
        Album album1 = Album.builder()
                .id(1L)
                .albumName("The Tortured Poets Department: The Anthology")
                .artist(new Artist("Taylor Swift"))
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseDate(LocalDate.parse("2024-05-05"))
                .stockQuantity(50L)
                .build();

        // Act
        when(recordShopService.getAlbumById(album1.getId())).thenReturn(Optional.of(album1));

        //Assert
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(album1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.albumName").value(album1.getAlbumName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value(album1.getArtist()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(album1.getGenre().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.format").value(album1.getFormat().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(album1.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate").value(album1.getReleaseDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockQuantity").value(album1.getStockQuantity()));
    }

    @Test
    @DisplayName("Tests getAlbumsById method if the id not found")
    void test_GetAlbumsById_NotFound() throws Exception {

        //Arrange
        Long id = 0L;

        //Act
        when(recordShopService.getAlbumById(id)).thenReturn(Optional.empty());

        //Assert
        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/albums/"+ id))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }
}


package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Format;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.repository.RecordShopRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@DataJpaTest
public class RecordShopServiceTests {

    @Mock
    private RecordShopRepository recordShopRepository;

    @InjectMocks
    private RecordShopServiceImpl recordShopServiceImpl;

    @Test
    public void test_GetAllAlbums() {
        //Arrange
        List<Album> albumList = new ArrayList<>();
        Album album1 = Album.builder()
                .id(1L)
                .albumName("The Tortured Poets Department: The Anthology")
                .artist("Taylor Swift")
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseYear(Integer.parseInt("2024"))
                .stockQuantity(50L)
                .build();
        albumList.add(album1);

        Album album2 = Album.builder()
                .id(2L)
                .albumName("B-Sides And Otherwise")
                .artist("Morphine")
                .genre(Genre.ROCK)
                .format(Format.VINYL)
                .price(11.95)
                .releaseYear(Integer.parseInt("2024"))
                .stockQuantity(98L)
                .build();
        albumList.add(album2);

        //Act
        when(recordShopRepository.findAll()).thenReturn(albumList);
        List<Album> actualResult = recordShopServiceImpl.getAllAlbums();

        //Assert
        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(albumList);
    }

    @Test
    public void test_GetAlbumById() {
        Album album1 = Album.builder()
                .id(1L)
                .albumName("The Tortured Poets Department: The Anthology")
                .artist("Taylor Swift")
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseYear(Integer.parseInt("2024"))
                .stockQuantity(50L)
                .build();

        //Arrange
        when(recordShopRepository.findById(album1.getId())).thenReturn(Optional.of(album1));
        Optional<Album> actualAlbum = recordShopServiceImpl.getAlbumById(album1.getId());

        //Assert
        assertThat(actualAlbum.get().getAlbumName()).as("The Tortured Poets Department: The Anthology");
        assertThat(actualAlbum.get().getArtist()).as("Taylor Swift");
        assertThat(actualAlbum.get().getGenre()).as(String.valueOf(Genre.POP));
        assertThat(actualAlbum.get().getFormat()).as(String.valueOf(Format.CD));
        assertThat(actualAlbum.get().getPrice()).as(String.valueOf(16.99));
        assertThat(actualAlbum.get().getReleaseYear());
        assertThat(actualAlbum.get().getStockQuantity()).as(String.valueOf(50L));
    }

    @Test
    public void test_AddAlbum() {
        //Arrange
        Album newAlbum = Album.builder()
                .id(1L)
                .albumName("The Tortured Poets Department: The Anthology")
                .artist("Taylor Swift")
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseYear(Integer.parseInt("2024"))
                .stockQuantity(50L)
                .build();

        //Act
        when(recordShopRepository.save(newAlbum)).thenReturn(newAlbum);
        Album actualResult = recordShopServiceImpl.insertAlbum(newAlbum);

        //Assert
        assertThat(actualResult).isEqualTo(newAlbum);
    }

    @Test
    public void test_updateAlbum() {
        //Arrange
        Album album = Album.builder()
                .id(1L)
                .albumName("The Tortured Poets Department: The Anthology")
                .artist("Taylor Swift")
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseYear(Integer.parseInt("2024"))
                .stockQuantity(50L)
                .build();

        Album updatedAlbum = Album.builder()
                .albumName("B-sides And Otherwise")
                .artist("Morphine")
                .genre(Genre.ROCK)
                .format(Format.VINYL)
                .price(11.95)
                .releaseYear(Integer.parseInt("2024"))
                .stockQuantity(98L)
                .build();

        when(recordShopRepository.findById(album.getId())).thenReturn(Optional.of(album));
        when(recordShopRepository.save(album)).thenReturn(updatedAlbum);

        //Act
        Album expectedResult = recordShopServiceImpl.updateAlbum(album.getId(), updatedAlbum);

        //Assert
        assertThat(expectedResult).isEqualTo(updatedAlbum);

    }



}

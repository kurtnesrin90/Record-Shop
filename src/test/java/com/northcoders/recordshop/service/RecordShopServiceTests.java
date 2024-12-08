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
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(actualAlbum.isPresent());
        assertThat(actualAlbum.get().getAlbumName()).isEqualTo(album1.getAlbumName());
        assertThat(actualAlbum.get().getArtist()).isEqualTo(album1.getArtist());
        assertThat(actualAlbum.get().getGenre()).isEqualTo(album1.getGenre());
        assertThat(actualAlbum.get().getFormat()).isEqualTo(album1.getFormat());
        assertThat(actualAlbum.get().getPrice()).isEqualTo(album1.getPrice());
        assertThat(actualAlbum.get().getReleaseYear()).isEqualTo(album1.getReleaseYear());
        assertThat(actualAlbum.get().getStockQuantity()).isEqualTo(album1.getStockQuantity());
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

    @Test
    public void test_deleteAlbum() {
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

        //Act
        when(recordShopRepository.findById(album.getId())).thenReturn(Optional.of(album));
        String expectedMessage = recordShopServiceImpl.deleteById(album.getId());

        //Assert
        assertThat(expectedMessage).isEqualTo("Deleting success!");

    }



}

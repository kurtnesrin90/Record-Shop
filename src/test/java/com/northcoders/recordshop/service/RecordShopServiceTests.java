package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.model.Format;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.repository.RecordShopRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
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
                .artist(new Artist("Taylor Swift"))
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseDate(LocalDate.parse("2024-05-05"))
                .stockQuantity(50L)
                .build();
        albumList.add(album1);

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
                .artist(new Artist("Taylor Swift"))
                .genre(Genre.POP)
                .format(Format.CD)
                .price(16.99)
                .releaseDate(LocalDate.parse("2024-05-05"))
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
        assertThat(actualAlbum.get().getReleaseDate()).as(String.valueOf(LocalDate.parse ("2024-05-05")));
        assertThat(actualAlbum.get().getStockQuantity()).as(String.valueOf(50L));
    }
}

package com.northcoders.recordshop.service;

import com.northcoders.recordshop.DTO.AlbumDto;
import com.northcoders.recordshop.model.Album;
import java.util.List;
import java.util.Optional;

public interface RecordShopService {

    // This interface will have the method signatures

    List<Album> getAllAlbums();

    Optional<Album> getAlbumById(Long id);

    Album insertAlbum(Album album);
}

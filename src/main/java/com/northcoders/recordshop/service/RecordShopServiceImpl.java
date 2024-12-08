package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.repository.RecordShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//This class will have the implementations for the methods

@Service
public class RecordShopServiceImpl implements RecordShopService {

    @Autowired
    RecordShopRepository recordShopRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        recordShopRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Optional<Album> getAlbumById(Long id) {
        Optional<Album> existingAlbum = recordShopRepository.findById(id);
        if(existingAlbum.isEmpty()){
            throw new RuntimeException("Album could not found by this id: " + id);
        } else {
            return existingAlbum;
        }
    }

    @Override
    public Album insertAlbum(Album album) {
        return recordShopRepository.save(album);
    }

    @Override
    public Album updateAlbum(Long id , Album album) {
        Album foundAlbumById = recordShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found with this id: " + id));
        foundAlbumById.setAlbumName(album.getAlbumName());
        foundAlbumById.setArtist(album.getArtist());
        foundAlbumById.setFormat(album.getFormat());
        foundAlbumById.setPrice(album.getPrice());
        foundAlbumById.setGenre(album.getGenre());
        foundAlbumById.setReleaseYear(album.getReleaseYear());
        foundAlbumById.setStockQuantity(album.getStockQuantity());

        return recordShopRepository.save(foundAlbumById);

    }

    @Override
    public String deleteById(Long id) {
        Album existingAlbum = recordShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Album could not found with this id: " + id));
        recordShopRepository.delete(existingAlbum);
        return "Deleting success!";
    }


}

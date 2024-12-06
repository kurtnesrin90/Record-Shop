package com.northcoders.recordshop.controller;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.service.RecordShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/albums")
public class RecordShopController {

    @Autowired
    RecordShopService recordShopService;

    @GetMapping()
    public ResponseEntity<List<Album>> getAllAlbums(){
        List<Album> albums = recordShopService.getAllAlbums();
        return new ResponseEntity<>(albums , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Album>> getAlbumById(@PathVariable Long id){
        Optional<Album> albumById = recordShopService.getAlbumById(id);
        if(albumById.isEmpty()){
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(albumById);
        }
    }
}

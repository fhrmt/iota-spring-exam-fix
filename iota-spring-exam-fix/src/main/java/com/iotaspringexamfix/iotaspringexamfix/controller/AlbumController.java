package com.iotaspringexamfix.iotaspringexamfix.controller;

import com.iotaspringexamfix.iotaspringexamfix.entity.Album;
import com.iotaspringexamfix.iotaspringexamfix.exception.ValidationException;
import com.iotaspringexamfix.iotaspringexamfix.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album) throws ValidationException {
        try {
            Album result = albumService.createAlbum(album);
            return result;
        } catch (ValidationException ve) {
            throw new ResponseStatusException(ve.getHttpStatus(), ve.getMessage());
        }
    }

    @PutMapping("/updatealbum/{id}")
    public Album updateAlbum(@PathVariable("id") String id, @RequestBody Album album) {
        try {
            Album updatedAlbum = albumService.updateAlbum(id, album);
            return updatedAlbum;
        } catch (ValidationException ve) {
            throw new ResponseStatusException(ve.getHttpStatus(), "Album with given id not found");
        }
    }

    @GetMapping("/getalbums")
    public List<Album> findAllAlbum() {
        return albumService.listAlbums();
    }

    @GetMapping("/getsuperalbumsbyid/{id}")
    public Album findSuperAlbumById(@PathVariable(name = "id") String id) throws Exception {
        
        Optional<Album> album = Optional.ofNullable(albumService.getAlbumsById(id));

        if (album.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No album this id");
        }
        return album.get();
    }
}

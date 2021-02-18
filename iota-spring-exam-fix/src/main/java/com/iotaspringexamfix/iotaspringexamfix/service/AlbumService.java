package com.iotaspringexamfix.iotaspringexamfix.service;

import com.iotaspringexamfix.iotaspringexamfix.entity.Album;
import com.iotaspringexamfix.iotaspringexamfix.exception.ValidationException;
import com.iotaspringexamfix.iotaspringexamfix.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }


    public Album createAlbum(Album album) throws ValidationException {
        if (album.getTitle() == null || album.getTitle() =="") {
            throw new ValidationException("title can not be null or empty string!", HttpStatus.BAD_REQUEST);
        }
        if (album.getCount() <= 0) {
            throw new ValidationException("counter has to be more than 0!", HttpStatus.BAD_REQUEST);
        }else {
            Album result = albumRepository.save(album);
            return result;
        }

    }

    public List<Album> listAlbums() {
        List<Album> albumListList = albumRepository.findAll();
        return albumListList;
    }

    public Album getAlbumsById(String id) throws Exception {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isEmpty()) {
            throw new ValidationException("There is no album with such id. ", HttpStatus.BAD_REQUEST);
        }
        Album album = optionalAlbum.get();
        return album;
    }

    public Album updateAlbum(String id, Album album) throws ValidationException {
       Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isEmpty()) {
            throw new ValidationException("There is no such Id", HttpStatus.BAD_REQUEST);
        }
        Album actualAlbum = optionalAlbum.get();
        actualAlbum.setTitle(album.getTitle());
        actualAlbum.setCount(album.getCount());
        Album updated = albumRepository.save(actualAlbum);
        return updated;
    }
}

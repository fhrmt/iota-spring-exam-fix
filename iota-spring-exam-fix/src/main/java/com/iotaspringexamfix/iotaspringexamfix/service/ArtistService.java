package com.iotaspringexamfix.iotaspringexamfix.service;

import com.iotaspringexamfix.iotaspringexamfix.entity.Artist;
import com.iotaspringexamfix.iotaspringexamfix.exception.ValidationException;
import com.iotaspringexamfix.iotaspringexamfix.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createArtist(Artist artist) throws ValidationException {
        if (artist.getFirstname() == null || artist.getFirstname() == "") {
            throw new ValidationException("firstname cant be null or empty string!", HttpStatus.BAD_REQUEST);
        }
        if (artist.getLastname() == null || artist.getLastname() == "") {
            throw new ValidationException("lastname cant be null or empty string!", HttpStatus.BAD_REQUEST);
        } else {
            Artist result = artistRepository.save(artist);
            return result;
        }
    }

    public List<Artist> listArtists() {
        List<Artist> artistList = artistRepository.findAll();
        return artistList;
    }

    public Artist getArtistsById(String id) throws Exception {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isEmpty()) {
            throw new ValidationException("There is no artist with such id. ", HttpStatus.BAD_REQUEST);
        }
        Artist artist = optionalArtist.get();
        return artist;
    }

    public Artist updateArtist(String id, Artist artist) throws ValidationException {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isEmpty()) {
            throw new ValidationException("There is no such Id", HttpStatus.BAD_REQUEST);
        }
        Artist actualArtist = optionalArtist.get();
        actualArtist.setFirstname(actualArtist.getFirstname());
        actualArtist.setLastname(actualArtist.getLastname());
        Artist updated = artistRepository.save(actualArtist);
        return updated;
    }
}

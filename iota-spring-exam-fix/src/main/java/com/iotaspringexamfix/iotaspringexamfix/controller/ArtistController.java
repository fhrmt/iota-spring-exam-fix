package com.iotaspringexamfix.iotaspringexamfix.controller;

import com.iotaspringexamfix.iotaspringexamfix.entity.Artist;
import com.iotaspringexamfix.iotaspringexamfix.exception.ValidationException;
import com.iotaspringexamfix.iotaspringexamfix.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist) throws ValidationException {
        try {
            Artist result = artistService.createArtist(artist);
            return result;
        } catch (ValidationException ve) {
            throw new ResponseStatusException(ve.getHttpStatus(), ve.getMessage());
        }

    }

    @PutMapping("/updateartist/{id}")
    public Artist updateArtist(@PathVariable("id") String id, @RequestBody Artist artist) {
         try {
             Artist updatedArtist = artistService.updateArtist(id, artist);
            return updatedArtist;
        } catch (ValidationException ve) {
            throw new ResponseStatusException(ve.getHttpStatus(), "Artist with given id not found");
        }
    }

    @GetMapping("/getartists")
    public List<Artist> findAllArtists() {
        return artistService.listArtists();
    }

    @GetMapping("/getartistsbyid/{id}")
    public Artist findArtistById(@PathVariable(name = "id") String id) throws Exception {

        Optional<Artist> artist = Optional.ofNullable(artistService.getArtistsById(id));

        if (artist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No artist this id");
        }
        return artist.get();
    }
}

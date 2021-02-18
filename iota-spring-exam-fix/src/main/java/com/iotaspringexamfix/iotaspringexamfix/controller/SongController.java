package com.iotaspringexamfix.iotaspringexamfix.controller;

import com.iotaspringexamfix.iotaspringexamfix.entity.Album;
import com.iotaspringexamfix.iotaspringexamfix.entity.Song;
import com.iotaspringexamfix.iotaspringexamfix.exception.ValidationException;
import com.iotaspringexamfix.iotaspringexamfix.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Song createSong(@RequestBody Song song) throws ValidationException {
        try {
            Song result = songService.createSong(song);
            return result;
        } catch (ValidationException ve) {
            throw new ResponseStatusException(ve.getHttpStatus(), ve.getMessage());
        }
    }

    @PutMapping("/updateSong/{id}")
    public Song updateSong(@PathVariable("id") String id, @RequestBody Song song) {
        try {
            Song updatedSong = songService.updateSong(id, song);
            return updatedSong;
        } catch (ValidationException ve) {
            throw new ResponseStatusException(ve.getHttpStatus(), "Song with given id not found");
        }
    }

    @GetMapping("/getsongs")
    public List<Song> findAllSong() {
        return songService.listSongs();
    }

    @GetMapping("/getsupersongsbyid/{id}")
    public Song findSuperSongById(@PathVariable(name = "id") String id) throws Exception {

        Optional<Song> song = Optional.ofNullable(songService.getSongsById(id));

        if (song.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No song this id");
        }
        return song.get();
    }
}

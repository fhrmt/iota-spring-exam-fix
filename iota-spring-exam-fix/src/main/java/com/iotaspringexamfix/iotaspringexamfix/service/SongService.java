package com.iotaspringexamfix.iotaspringexamfix.service;


import com.iotaspringexamfix.iotaspringexamfix.entity.Artist;
import com.iotaspringexamfix.iotaspringexamfix.entity.Song;
import com.iotaspringexamfix.iotaspringexamfix.exception.ValidationException;
import com.iotaspringexamfix.iotaspringexamfix.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song createSong(Song song) throws ValidationException {
        if (song.getTitle() == null || song.getTitle() == "") {
            throw new ValidationException("title cant be null or empty string!", HttpStatus.BAD_REQUEST);
        }
        if (song.getArtist() == null || song.getArtist().getId() == 0) {
            throw new ValidationException("artist cant be null or empty string!", HttpStatus.BAD_REQUEST);

        }
        if (song.getLength() <= 0) {
            throw new ValidationException("a length nem lehet 0 vagy ksiebb!", HttpStatus.BAD_REQUEST);
        }
        if (song.getYear() == null) {
            throw new ValidationException("a year nem lehet null!", HttpStatus.BAD_REQUEST);
        } else {
            Song result = songRepository.save(song);
            return result;
        }
    }

    public List<Song> listSongs() {
        List<Song> songList = songRepository.findAll();
        return songList;
    }

    public Song getSongsById(String id) throws Exception {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isEmpty()) {
            throw new ValidationException("There is no song with such id. ", HttpStatus.BAD_REQUEST);
        }
        Song song = optionalSong.get();
        return song;
    }

    public Song updateSong(String id, Song song) throws ValidationException {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isEmpty()) {
            throw new ValidationException("There is no such Id", HttpStatus.BAD_REQUEST);
        }
        Song actualSong = optionalSong.get();
        actualSong.setAlbum(actualSong.getAlbum());
        actualSong.setArtist(actualSong.getArtist());
        actualSong.setGenre(actualSong.getGenre());
        actualSong.setLength(actualSong.getLength());
        actualSong.setLyrics(actualSong.getLyrics());


        Song updated = songRepository.save(actualSong);
        return updated;
    }
}

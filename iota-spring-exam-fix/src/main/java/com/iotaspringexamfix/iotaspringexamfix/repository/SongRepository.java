package com.iotaspringexamfix.iotaspringexamfix.repository;

import com.iotaspringexamfix.iotaspringexamfix.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, String> {
}

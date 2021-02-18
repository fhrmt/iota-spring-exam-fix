package com.iotaspringexamfix.iotaspringexamfix.repository;

import com.iotaspringexamfix.iotaspringexamfix.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {
}

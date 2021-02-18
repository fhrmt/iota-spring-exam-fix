package com.iotaspringexamfix.iotaspringexamfix.repository;

import com.iotaspringexamfix.iotaspringexamfix.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {
}

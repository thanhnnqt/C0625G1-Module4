package org.example.song_manage.repository;

import org.example.song_manage.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<Song, Integer> {
    Page<Song> findAllByNameContaining(String keyword, Pageable pageable);
}

package org.example.song_manage.service;

import org.example.song_manage.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISongService {
    Page<Song> findAllByNameContaining(String keyword, Pageable pageable);
    void save(Song song);
    Song findById(Integer id);
    void addSong(Song user);
}

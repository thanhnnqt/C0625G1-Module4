package org.example.song_manage.service;

import org.example.song_manage.entity.Song;
import org.example.song_manage.repository.ISongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SongService implements ISongService{
    private final ISongRepository iSongRepository;
    public SongService(ISongRepository iSongRepository){
        this.iSongRepository = iSongRepository;
    }

    @Override
    public Page<Song> findAllByNameContaining(String keyword, Pageable pageable) {
        return iSongRepository.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public void save(Song song) {
        iSongRepository.save(song);
    }

    @Override
    public Song findById(Integer id) {
        return iSongRepository.findById(id).orElse(null);
    }

    @Override
    public void addSong(Song song) {
        iSongRepository.save(song);
    }
}

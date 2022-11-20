package org.example.services;
import org.example.repositories.SongRepository;
import org.example.models.Song;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class SongService {
   // SongRepository repo;

    public ArrayList<Song> songs() {
       return SongRepository.initSongs();
    }
}

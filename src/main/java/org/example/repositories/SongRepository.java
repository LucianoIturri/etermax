package org.example.repositories;

import org.example.models.Song;

import java.util.ArrayList;

public class SongRepository {
    private static ArrayList<Song> songs;

    public static void init(){
        getSongs();
    }

    public static ArrayList<Song> getSongs(){
        songs = new ArrayList<Song>();
        songs.add(new Song("Californication", "RHCP", "Californication"));
        songs.add(new Song("Bring Me To Life", "Evanescence", "Fallen"));
        songs.add(new Song("Scar Tissues", "RHCP", "Californication"));
        songs.add(new Song("Around the World", "RHCP", "Californication"));
        songs.add(new Song("Other Side", "RHCP", "Californication"));
        songs.add(new Song("Get On Top", "RHCP", "Californication"));
        songs.add(new Song("Parallel Universe", "RHCP", "Californication"));
        songs.add(new Song("Somewhere I Belong", "Linkin Park", "Meteora"));
        songs.add(new Song("Numb", "Linkin Park", "Meteora"));
        songs.add(new Song("Easier To run", "Linkin Park", "Meteora"));

        return songs;
    }
}

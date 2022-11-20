package org.example.controllers;
import org.example.services.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.models.Song;

import java.util.ArrayList;

@RestController
public class SongController {
    private final SongService service;

    public SongController(SongService service) {
        this.service = service;
    }

    @GetMapping("/songs")
    public ArrayList<Song> songs(){
        return service.songs();
    }
}

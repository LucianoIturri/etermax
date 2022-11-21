package org.example.controllers;

import java.util.ArrayList;
import java.util.Locale;

import com.google.gson.Gson;
import org.example.models.List;
import org.example.models.Song;
import org.example.services.ListService;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/list")
public class ListController {
   private final ListService service;
   private final Gson gson;

    public ListController(ListService service){
        this.service = service;
        this.gson = new Gson();
    }

    @GetMapping("/show")
    public ArrayList<List> getLists() {
        return service.getLists();
    }

    @RequestMapping(path="/create",method = POST, consumes = MediaType.ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<List> createList(HttpEntity<String> httpEntity) {
        List list = gson.fromJson(httpEntity.getBody(), List.class);
        this.service.createList(list);

        return this.getLists();
    }

    @RequestMapping(path="/edit",method = PUT, consumes = MediaType.ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<List> editList(HttpEntity<String> httpEntity, @RequestParam String id) {
        List list = gson.fromJson(httpEntity.getBody(), List.class);
        this.service.editList(list, id);

        return this.getLists();
    }

    @RequestMapping(path="/delete",method = DELETE, consumes = MediaType.ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<List> editList(@RequestParam String id) {
        this.service.deleteList(id);

        return this.getLists();
    }

    @RequestMapping(path="/search-lists",method = GET, consumes = MediaType.ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<List> getBySong(@RequestParam String song_title) {
        return this.service.getBySong(song_title);
    }
}

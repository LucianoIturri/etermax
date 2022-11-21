package org.example.controllers;

import java.awt.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import org.example.models.List;
import org.example.services.ListService;
import org.example.security.BearerToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/list")
public class ListController {
   private final ListService service;
   private final Gson gson;
   private BearerToken bearer_token;
   @Value("${BEARER_TOKEN}")
   private String token;

    public ListController(ListService service, BearerToken bearer_token){
        this.service = service;
        this.gson = new Gson();
        this.bearer_token = bearer_token;
    }

    @GetMapping("/show")
    public ArrayList<List> getLists() {
        if(this.is_authorized()){
            return service.getLists();
        }
    return null;
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
        if(this.is_authorized()) {
            List list = gson.fromJson(httpEntity.getBody(), List.class);
            this.service.editList(list, id);

            return this.getLists();
        }
        return null;
    }

    @RequestMapping(path="/delete",method = DELETE, consumes = MediaType.ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<List> editList(@RequestParam String id) {
        if(this.is_authorized()) {
            this.service.deleteList(id);

            return this.getLists();
        }

        return null;
    }

    @RequestMapping(path="/search-lists",method = GET, consumes = MediaType.ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<List> getBySong(@RequestParam String song_title) {
        if(this.is_authorized()) {
            return this.service.getBySong(song_title);
        }
        return null;
    }

    private Boolean is_authorized(){
        return this.bearer_token.getToken().equals(token);
    }
}

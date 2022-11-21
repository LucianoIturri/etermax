package org.example.services;
import org.example.models.List;
import org.example.models.Song;
import org.example.repositories.ListRepository;
import org.example.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class ListService {
   private final ListRepository repo;
    public ListService(ListRepository repo){
        this.repo = repo;
    }

    public ArrayList<List> getLists() {
        return this.repo.getLists();
    }

    public ArrayList<List> createList(List list){
        Integer size = this.repo.getLists().size();
        list.setPosition(size);
        this.repo.insertList(list);

        return this.repo.getLists();
    }
    
    public ArrayList<List> editList(List list, String id){
        this.updateList(this.repo.getById(id), list);

        return this.repo.getLists();
    }

    public ArrayList<List> deleteList(String id){
        this.deleteList(this.repo.getById(id));

        return this.repo.getLists();
    }

    public  ArrayList<List> getBySong(String song_title){
        ArrayList<List> carrier_lists = new ArrayList<List>();
        String song_title_unquoted = song_title.replaceAll("^\"|\"$", "");
        for(List list: this.repo.getLists()){
            if(this.listHasSong(list, song_title_unquoted)){
                carrier_lists.add(list);
            }
        }

        return carrier_lists;
    }

    private void updateList(List found_list, List list){
        found_list.setName(list.getName());
        found_list.setSongs(list.getSongs());
        this.repo.updateList(found_list);
    }

    private void deleteList(List found_list){
        this.repo.deleteList(found_list);
    }

    private Boolean listHasSong(List list, String song_title){
        Boolean it_has = false;

        for(Song song: list.getSongs()){
            if(song.getTitle().equals(song_title)){
                it_has = true;
            }
        }

        return it_has;
    }
}

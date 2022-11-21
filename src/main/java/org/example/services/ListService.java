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
        list.setId(this.repo.getLists().size() +1);
        this.repo.getLists().add(list);

        return this.repo.getLists();
    }
    
    public ArrayList<List> editList(List list, String id){
        this.updateList(this.searchForList(id), list);

        return this.repo.getLists();
    }

    public ArrayList<List> deleteList(String id){
        this.deleteList(this.searchForList(id));

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
    private List searchForList(String id){
        List found_list = null;
        ArrayList<List> lists = this.repo.getLists();
        Iterator<List> iterator = lists.iterator();
        while(iterator.hasNext()){
            List aux = iterator.next();
            if(aux.getId() == Integer.parseInt(id)){
                found_list = aux;
            }
        }

        return found_list;
    }

    private void updateList(List found_list, List list){
        found_list.setName(list.getName());
        found_list.setSongs(list.getSongs());
        this.repo.getLists().set(found_list.getPosition(), found_list);
    }

    private void deleteList(List found_list){
        this.repo.getLists().remove(found_list.getPosition());
    }

    private Boolean listHasSong(List list, String song_title){
        System.out.println(list.getSongs().get(0).getTitle().equals(song_title));
        System.out.println(song_title);
        System.out.println(list.getSongs().get(0).getTitle());

        Boolean it_has = false;
        for(Song song: list.getSongs()){
            if(song.getTitle().equals(song_title)){
                it_has = true;
            }
        }

        return it_has;
    }
}

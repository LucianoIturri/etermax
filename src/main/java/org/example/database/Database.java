package org.example.database;

import com.google.gson.Gson;
import org.example.models.Song;
import org.example.models.List;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class Database {
    private static ArrayList<List> lists;
    private static ArrayList<Song> songs;
    private final Gson gson;

    public Database(Gson gson){
        this.gson = gson;
    }
    public static void init(){
        initSongs();
        initLists();
    }

    public static void initLists(){
        lists = new ArrayList<List>();
        ArrayList<Song> playlist1 = new ArrayList<>();
        ArrayList<Song> playlist2 = new ArrayList<>();
        ArrayList<Song> playlist3 = new ArrayList<>();
        ArrayList<Song> playlist4 = new ArrayList<>();

        playlist1.add(getSongs().get(0));
        playlist1.add(getSongs().get(2));
        playlist1.add(getSongs().get(4));

        playlist2.add(getSongs().get(1));
        playlist2.add(getSongs().get(3));
        playlist2.add(getSongs().get(5));

        playlist3.add(getSongs().get(4));
        playlist3.add(getSongs().get(6));
        playlist3.add(getSongs().get(8));

        playlist4.add(getSongs().get(5));
        playlist4.add(getSongs().get(7));
        playlist4.add(getSongs().get(9));

        lists.add(new List(1, "List I", playlist1, 0));
        lists.add(new List(2, "List II", playlist2, 1));
        lists.add(new List(3, "List III", playlist3, 2));
        lists.add(new List(4, "List IV", playlist4, 3));
    }
    public static void initSongs(){
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
    }
    public String lists(){
        String json_lists = this.gson.toJson(this.getLists());

        return json_lists;
    }

    public String getListById(String id){
        List found_list = null;
        for(List list: this.getLists()){
            if(list.getId() == Integer.parseInt(id)){
                found_list = list;
            }
        }
        String json_lists = this.gson.toJson(found_list);

        return json_lists;
    }

    public String insertLists(List list){
        Integer size = this.getLists().size();
        Integer id = 1;
        if(size>0){
            List last = this.getLists().get(size - 1);
            id = last.getId() +1;
        }
        list.setId(id);
        this.getLists().add(list);

        return this.lists();
    }

    public String updateList(List list){
        this.getLists().set(list.getPosition(), list);

        return this.lists();
    }

    public String deleteList(List list){
        this.getLists().remove(list.getPosition());
        this.updatePositions();

        return this.lists();
    }

    private void setLists(ArrayList<List> lists){
        this.lists = lists;
    }
    private void updatePositions(){
        ArrayList<List> aux = new ArrayList<List>();
        Integer loop = 0;
        for(List list: this.getLists()){
            list.setPosition(loop);
            aux.add(list);
            loop++;
        }
        this.setLists(aux);
    }

    public static ArrayList<List> getLists() {
        return lists;
    }
    public static ArrayList<Song> getSongs() {
        return songs;
    }
}

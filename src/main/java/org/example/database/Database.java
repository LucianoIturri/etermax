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
        initLists();
    }

    public static void initLists(){
        lists = new ArrayList<List>();
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
    public ArrayList<List> getLists() {
        return lists;
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
}

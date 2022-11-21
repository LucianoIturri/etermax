package org.example.repositories;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.database.Database;
import org.example.models.List;
import org.example.models.Song;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
@Repository
public class ListRepository {
    private final Gson gson;
    private Database database;

    public ListRepository(Gson gson,Database database){
        this.gson = gson;
        this.database = database;
    }

    public ArrayList<List> insertList(List list){
        this.database.insertLists(list);

        return this.getLists();
    }

    public ArrayList<List> updateList(List list){
        this.database.updateList(list);

        return this.getLists();
    }
     public ArrayList<List> getLists(){
        System.out.println(database.lists());
            return gson.fromJson(database.lists(), new TypeToken<ArrayList<List>>(){}.getType());
     }

    public ArrayList<List> deleteList(List list){
        return gson.fromJson(database.deleteList(list), new TypeToken<ArrayList<List>>(){}.getType());
    }
    public List getById(String id){
        return gson.fromJson(database.getListById(id), List.class);
    }

}

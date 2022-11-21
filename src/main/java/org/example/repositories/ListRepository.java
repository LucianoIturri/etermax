package org.example.repositories;
import org.example.models.List;
import org.example.models.Song;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
@Repository
public class ListRepository {
    private static ArrayList<List> list;


    public static void init(){
        initLists();
    }

    public static void initLists(){
        list = new ArrayList<List>();
    }

    public static ArrayList<List> getLists() {
        return list;
    }
}

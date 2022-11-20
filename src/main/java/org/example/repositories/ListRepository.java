package org.example.repositories;
import org.example.models.List;
import org.example.models.Song;

import java.util.ArrayList;

public class ListRepository {
    private static ArrayList<List> list;


    public static void init(){
        getLists();
    }

    public static ArrayList<List> getLists(){
        list = new ArrayList<List>();

        return list;
    }
}

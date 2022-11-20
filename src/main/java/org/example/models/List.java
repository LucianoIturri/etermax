package org.example.models;
import lombok.Data;
import org.example.models.*;
import java.util.ArrayList;

@Data
public class List {
    private String name;
    private ArrayList<Song> songs;
}

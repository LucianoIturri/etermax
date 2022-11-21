package org.example.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.models.*;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class List {
    private int id;
    private String name;
    private ArrayList<Song> songs;
    private int position;
}

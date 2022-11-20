package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Song {
    private String title;
    private String artist;
    private String album;
}

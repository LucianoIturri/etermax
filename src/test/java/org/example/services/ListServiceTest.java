package org.example.services;

import org.example.models.Song;
import org.example.repositories.ListRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.example.models.List;
import org.example.repositories.*;
import java.util.ArrayList;
import org.example.database.Database;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListServiceTest {
    @InjectMocks
    public ListService service;
    @Mock
    public ListRepository repo;

    public Database db;
    private List list;
    private Song song;
    private ArrayList<Song> songs;
    private ArrayList<List> lists;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Database.init();
        lists = mock(ArrayList.class);

    }

    @Test
    void getLists() {
        when(service.getLists())
                .thenReturn(Database.getLists());
        assertNotNull(service.getLists());
        assertEquals(service.getLists(), Database.getLists());
    }

    @Test
    void createList() {
        list = this.createTestList();
        lists = new ArrayList<List>();
        lists.add(list);

        when(service.createList(list))
                .thenReturn(lists);
        assertEquals(list, new List(1, "Test List", songs, 0), "PASSED");
        assertNotEquals(list, new List(1, "Other List", songs, 0), "PASSED");
        assertEquals(lists.size(), 1, "PASSED");
        assertNotNull(service.createList(list), "PASSED");
    }

    @Test
    void editList() {
        List list = this.createTestList();
        Database.getLists().add(list);
        when(service.editList(list, "1"))
              .thenReturn(Database.getLists());

    }

    @Test
    void deleteList() {
        Integer size_before = service.getLists().size();
        when(service.deleteList("1"))
                .thenReturn(Database.getLists());

        Integer size_after = service.getLists().size();

        assertEquals(size_before - 1, size_after);
    }

    @Test
    void getBySong() {

        when(service.getBySong("Califonication"))
                .thenReturn(Database.getLists());

        assertEquals(Database.getLists().get(0),service.getBySong("Califonication"));
    }

    private List createTestList(){
        songs = SongRepository.getSongs();
        return new List(1, "Test List", songs, 0);
    }
}
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListServiceTest {
    @InjectMocks
    private ListService service;
    @Mock
    private ListRepository repo;
    private List list;
    private Song song;
    private ArrayList<Song> songs;
    private ArrayList<List> lists;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        lists = mock(ArrayList.class);

    }

    @Test
    void getLists() {
        ArrayList<List> expected = new ArrayList<List>();
        when(service.getLists())
                .thenReturn(expected);
        assertNotNull(service.getLists());
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
        List current = this.createTestList();
        lists = new ArrayList<List>();
        lists.add(current);

        when(service.editList(current, Integer.toString(current.getId())))
                .thenReturn(lists);
        assertEquals(1,service.getLists().size());


    }

    @Test
    void deleteList() {
    }

    @Test
    void getBySong() {
    }

    private List createTestList(){
        songs = SongRepository.getSongs();
        return new List(1, "Test List", songs, 0);
    }
}
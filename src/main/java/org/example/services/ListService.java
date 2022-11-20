package org.example.services;
import org.example.repositories.ListRepository;

public class ListService {
    ListRepository repo;
    public ListService(ListRepository repo){
        this.repo = repo;
    }
}

package org.example.controllers;
import lombok.Data;
import org.example.services.ListService;


public class ListController {
    ListService service;

    public ListController(ListService service){
        this.service = service;
    }
}

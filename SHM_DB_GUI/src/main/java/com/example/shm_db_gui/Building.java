package com.example.shm_db_gui;

import java.util.ArrayList;

public class Building {
    String name;
    ArrayList<String> storages = new ArrayList<>();

    public Building(String name) {
        this.name = name;
    }
    public void listStorages(){
        for (String i : storages){
            System.out.println(i);
        }
    }
}

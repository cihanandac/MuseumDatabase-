package com.example.shm_db_gui;

import java.util.ArrayList;

public class Room {
    String name;
    Building building;
    ArrayList<Integer> Boxes = new ArrayList<>();

    public Room(String name){
        this.name = name;
    }
    public Room(String name, Building building){
        this.name = name;
        this.building = building;
    }

    public String listBoxes(){
        System.out.println("This room:");
        for(int i : Boxes) {
            System.out.println(i);
        }
        return "end for room";
    }
}

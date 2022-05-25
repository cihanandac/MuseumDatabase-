package com.example.shm_db_gui;

import java.util.ArrayList;

public class Box {
    int number;
    Room room;
    Boolean onTransport = false;
    ArrayList<Artefact> artefacts = new ArrayList<>();

    public Box(int number, Room room){
        this.number = number;
        this.room = room;
    }

    public void addArtefact(Artefact i){
        this.artefacts.add(i);
        i.box = this;
    }

    public void subArtefact(Artefact i){
        this.artefacts.remove(i);
    }

    public void listArtefacts(){
        System.out.println("in "+ this.number + " box:");
        for (Artefact i : artefacts) {
            System.out.println(i.number);
        }
    }

    public void relocate(Room room){
        this.room = room;
    }

    public void transport(){
        this.onTransport = true;
        this.room = null;
    }

    public void unload(Room room){
        this.onTransport = false;
        this.room = room;
    }

}

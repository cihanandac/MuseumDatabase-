package com.example.shm_db_gui;

public class Artefact {
    int id;
    String number;
    String name;
    String size;

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getBoxNo() {
        return boxNo;
    }

    String boxNo;
    Box box;
    Building building;
    Room room;
    String photo;

    public Artefact(int id, String number, String name, String size, String boxNo){
        this.id = id;
        this.number = number;
        this.name = name;
        this.size = size;
        this.boxNo = boxNo;
    }

    public void relocate(Box box){
        if(this.box == null){
            this.box = box;
            this.box.addArtefact(this);
        }
        else{
            this.box.subArtefact(this);
            box.addArtefact(this);
            this.box = box;
        }

    }

    //when the artefact is not in a box.
    public void relocate(Room room){
        this.box.subArtefact(this);
        this.box = null;
        this.room = room;
    }

}

package com.example.reneshn.whatsonza;

import java.util.ArrayList;

/**
 * Created by ReneshN on 2016/11/08.
 */
public class Venue {

    private int id;
    private String name;
    private EventLocation location;

    public Venue(int id, String name, String about, ArrayList<String> emails, String coverPic, String profilePic, EventLocation location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Venue() {
        this.id = 0;
        this.name = "";
        this.location = new EventLocation();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventLocation getLocation() {
        return location;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }
}

package com.example.reneshn.whatsonza;

/**
 * Created by ReneshN on 2016/11/11.
 */

public class Host {
    private int id;
    private String about,category,pictureURL,name;
    private EventLocation location;
    private Event_model eventList;

    public Host() {
    }

    public Host(String about, String category, Event_model eventList, int id, String pictureURL,String name,EventLocation location) {
        this.about = about;
        this.category = category;
        this.eventList = eventList;
        this.id = id;
        this.pictureURL = pictureURL;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public EventLocation getLocation() {
        return location;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Event_model getEventList() {
        return eventList;
    }

    public void setEventList(Event_model eventList) {
        this.eventList = eventList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}

package com.example.reneshn.whatsonza;

/**
 * Created by ReneshN on 2016/11/08.
 */

public class Event_model {
    private int id,timeFromNow;
    private String name,description,startTime,endTime,category;
    private Stats stats;
    private Venue venue;

    public Event_model(int id, String name, String description, String startTime, String endTime, int timeFromNow, String category, Stats stats, Venue venue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeFromNow = timeFromNow;
        this.category = category;
        this.stats = stats;
        this.venue = venue;
    }

    public Event_model() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.startTime = "";
        this.endTime = "";
        this.timeFromNow = 0;
        this.category = "";
        this.stats = new Stats();
        this.venue = new Venue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeFromNow() {
        return timeFromNow;
    }

    public void setTimeFromNow(int timeFromNow) {
        this.timeFromNow = timeFromNow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }


}

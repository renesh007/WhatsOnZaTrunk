package com.example.reneshn.whatsonza;

import java.util.ArrayList;

/**
 * Created by ReneshN on 2016/11/08.
 */
public class Venue {

    private int id;
    private String name,about,coverPic,profilePic;
    private ArrayList<String> emails;
    private EventLocation location;

    public Venue(int id, String name, String about, ArrayList<String> emails, String coverPic, String profilePic, EventLocation location) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.emails = emails;
        this.coverPic = coverPic;
        this.profilePic = profilePic;
        this.location = location;
    }

    public Venue() {
        this.id = 0;
        this.name = "";
        this.about = "";
        this.emails = new ArrayList<String>();
        this.coverPic = "";
        this.profilePic = "";
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public EventLocation getLocation() {
        return location;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }
}

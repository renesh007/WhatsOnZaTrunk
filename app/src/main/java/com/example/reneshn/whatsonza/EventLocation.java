package com.example.reneshn.whatsonza;

/**
 * Created by ReneshN on 2016/11/08.
 */
public class EventLocation {
    private String city,country,street,zip;
    private double latitude,longitude;


    public EventLocation(String city, String country, String street, String zip, double latitude, double longitude) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EventLocation() {
        this.city = "";
        this.country = "";
        this.street = "";
        this.zip = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

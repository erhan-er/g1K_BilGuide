package com.g1k.bilguide;

/**
 * Building class, initializes the buildings' names and coordinates.
 * @author Abdullah, Aslı
 * @version 1.0
 */

public class Building {

    // properties
    private String name;
    private double latitude;
    private double longitude;

    // constructors
    public Building(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // methods

    // get methods
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    // toString method
    @Override
    public String toString() {
        return name;
    }
}

package com.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Airport implements Comparable
{
    private String name;
    private double latitude;
    private double longitude;

    private static Airport searchable;


    public Airport(String name, double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public String getName() {return name;}
    public double getLatitude() {return latitude;}
    public double getLongitude() {return longitude;}
    public static Airport getSearchable() {return searchable;}

    @Override
    public String toString()
    {
        return name + ", Координаты: " + latitude + ", " + longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Double.compare(airport.latitude, latitude) == 0 && Double.compare(airport.longitude, longitude) == 0 && Objects.equals(name, airport.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, name);
    }

    @Override
    public int compareTo(Object o) {
        if(this == o)
            return 0;
        Airport other = (Airport) o;
        double first = this.lengthToSearchable();
        double second = other.lengthToSearchable();
        if(first<second)
            return -1;
        else if(first>second)
            return 1;
        else
            return 0;
    }

    public static void initializeSearchable() throws NumberFormatException, IOException
    {
        try {
            searchable = AirportParser.readFromConsole();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    public double lengthToSearchable()
    {
        double length = Math.sqrt((Math.pow(this.getLatitude()-searchable.getLatitude(), 2) +
                Math.pow(this.getLongitude() - searchable.getLongitude(), 2)));
        return length;
    }
}

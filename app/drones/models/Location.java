package drones.models;

import java.io.Serializable;

/**
 * Created by Cedric on 3/9/2015.
 */
public class Location implements Serializable {

    //Decimal Degrees = Degrees + minutes/60 + seconds/3600
    //https://en.wikipedia.org/wiki/Geographic_coordinate_conversion

    private double latitude;
    private double longtitude;
    private double heigth;

    public Location(double latitude, double longtitude, double heigth){
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.heigth = heigth;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongtitude(){
        return longtitude;
    }

    public double getHeigth(){
        return heigth;
    }

    public static double distance(Location l1, Location l2){
        // Harversine calculation
        //https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java

        double earthRadius = 6371000d; //meters
        double dLat = Math.toRadians(l2.getLatitude()-l1.getLatitude());
        double dLng = Math.toRadians(l2.getLongtitude()-l1.getLongtitude());
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(l1.getLatitude())) * Math.cos(Math.toRadians(l2.getLatitude())) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c;
    }

    public static short getDegrees(float num){
        return (short)Math.floor(num);
    }

    public static short getMinutes(float num){
        return (short)Math.floor((num - Math.floor(num)) * 60f); //cancellation yay!
    }

    public static short getSeconds(float num){
        return (short)Math.floor(3600 * (num - Math.floor(num) - (getMinutes(num)/60f)));
    }

    public static double fromDegrees(short degrees, short minutes, short seconds){
        return degrees + minutes/60f + seconds/3600f;
    }
}

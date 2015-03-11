package drones.messages;

import java.io.Serializable;

/**
 * Created by Cedric on 3/8/2015.
 */
public class LocationChangedMessage implements Serializable {

    public static final double UNAVAILABLE = -1.0d;

    private double longitude;
    private double latitude;
    private double gpsHeigth;

    public LocationChangedMessage(double longitude, double latitude, double gpsHeigth) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.gpsHeigth = gpsHeigth;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the altitude in meters
     * @return
     */
    public double getGpsHeigth() {
        return gpsHeigth;
    }
}
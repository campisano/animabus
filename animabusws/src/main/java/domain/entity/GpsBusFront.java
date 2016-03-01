package domain.entity;

public class GpsBusFront {

	private String timestamp;

    private String speed;

    private String altitude;

    private String altitude_accuracy;

    private String longitude;

    private String latitude;

    private String accuracy;

    private String heading;

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getSpeed ()
    {
        return speed;
    }

    public void setSpeed (String speed)
    {
        this.speed = speed;
    }

    public String getAltitude ()
    {
        return altitude;
    }

    public void setAltitude (String altitude)
    {
        this.altitude = altitude;
    }

    public String getAltitude_accuracy ()
    {
        return altitude_accuracy;
    }

    public void setAltitude_accuracy (String altitude_accuracy)
    {
        this.altitude_accuracy = altitude_accuracy;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getAccuracy ()
    {
        return accuracy;
    }

    public void setAccuracy (String accuracy)
    {
        this.accuracy = accuracy;
    }

    public String getHeading ()
    {
        return heading;
    }

    public void setHeading (String heading)
    {
        this.heading = heading;
    }
}

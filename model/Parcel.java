package model;

/**
 * Parcel class represents a parcel with various attributes.
 */
public class Parcel {
    private String parcelId;
    private int weight;
    private int length;
    private int width;
    private int height;
    private double distance;

    /**
     * Constructor initializes all attributes of the Parcel.
     *
     * @param parcelId  The unique identifier for the parcel.
     * @param weight    The weight of the parcel in kilograms.
     * @param length    The length of the parcel in centimeters.
     * @param width     The width of the parcel in centimeters.
     * @param height    The height of the parcel in centimeters.
     * @param distance  The delivery distance in kilometers.
     */
    public Parcel(String parcelId, int weight, int length, int width, int height, double distance) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.distance = distance;
    }

    // Getters
    public String getParcelId() {
        return parcelId;
    }

    public int getWeight() {
        return weight;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    // Other getters and setters as needed

    @Override
    public String toString() {
        return "Parcel ID: " + parcelId +
                ", Weight: " + weight +
                "kg, Distance: " + distance + "km";
    }
}

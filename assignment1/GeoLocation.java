//Geolocation Table
class GeoLocation {
    private String id;
    private String itemLatitude;
    private String itemLongitude;


    public GeoLocation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemLatitude() {
        return itemLatitude;
    }

    public void setItemLatitude(String itemLatitude) {
        this.itemLatitude = itemLatitude;
    }

    public String getItemLongitude() {
        return itemLongitude;
    }

    public void setItemLongitude(String itemLongitude) {
        this.itemLongitude = itemLongitude;
    }

    @Override
    public String toString() {
        return id + "\t" +
                itemLatitude + "\t" +
                itemLongitude;
    }
}
public class GeoLocation {
    private String itemID;
    private String itemLatitude;
    private String itemLongitude;


    public GeoLocation() {
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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
        return itemID + '\t' +
               itemLatitude + '\t' +
               itemLongitude;
    }
}

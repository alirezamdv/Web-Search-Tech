public class User {
    private String userID;
    private String sellerRating;
    private String bidderRating;
    private String userLocation;
    private String userCountry;

    public User() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getBidderRating() {
        return bidderRating;
    }

    public void setBidderRating(String bidderRating) {
        this.bidderRating = bidderRating;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    @Override
    public String toString() {
        return userID + '\t' +
               sellerRating + '\t' +
               bidderRating + '\t' +
               userLocation + '\t'+
               userCountry;
    }
}

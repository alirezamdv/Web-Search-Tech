public class Seller {
    private String sellerID;
    private String sellerRating;
    private String sellerLocation;
    private String sellerCountry;

    public Seller() {
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getSellerLocation() {
        return sellerLocation;
    }

    public void setSellerLocation(String sellerLocation) {
        this.sellerLocation = sellerLocation;
    }

    public String getSellerCountry() {
        return sellerCountry;
    }

    public void setSellerCountry(String sellerCountry) {
        this.sellerCountry = sellerCountry;
    }

    @Override
    public String toString() {
        return sellerID + '\t' +
               sellerRating + '\t' +
               sellerLocation + '\t'+
               sellerCountry;
    }
}

//Seller Table

class Seller {
    private String sellerID;
    private String sellerRating;

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

    @Override
    public String toString() {
        return sellerID + '\t' +
                sellerRating;
    }
}
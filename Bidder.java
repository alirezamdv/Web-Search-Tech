public class Bidder {
    private String bidderID;
    private String bidderRating;
    private String bidderLocation;
    private String bidderCountry;

    public Bidder() {
    }

    public String getBidderID() {
        return bidderID;
    }

    public void setBidderID(String bidderID) {
        this.bidderID = bidderID;
    }

    public String getBidderRating() {
        return bidderRating;
    }

    public void setBidderRating(String bidderRating) {
        this.bidderRating = bidderRating;
    }

    public String getBidderLocation() {
        return bidderLocation;
    }

    public void setBidderLocation(String bidderLocation) {
        this.bidderLocation = bidderLocation;
    }

    public String getBidderCountry() {
        return bidderCountry;
    }

    public void setBidderCountry(String bidderCountry) {
        this.bidderCountry = bidderCountry;
    }

    @Override
    public String toString() {
        return bidderID + '\t' +
               bidderRating + '\t' +
               bidderLocation + '\t'+
               bidderCountry;
    }
}

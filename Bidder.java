public class Bidder {
    private String bidderID;
    private String bidderRating;

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

    @Override
    public String toString() {
        return bidderID + '\t' +
               bidderRating;
    }
}

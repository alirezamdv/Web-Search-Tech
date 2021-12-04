public class Bids {
    private String bidderID;
    private String bidderTime;
    private String itemID;
    private String bidderAmount;

    public Bids() {
    }

    public String getBidderID() {
        return bidderID;
    }

    public void setBidderID(String bidderID) {
        this.bidderID = bidderID;
    }

    public String getBidderTime() {
        return bidderTime;
    }

    public void setBidderTime(String bidderTime) {
        this.bidderTime = bidderTime;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getBidderAmount() {
        return bidderAmount;
    }

    public void setBidderAmount(String bidderAmount) {
        this.bidderAmount = bidderAmount;
    }

    @Override
    public String toString() {
        return bidderID + '\t' +
                bidderTime + '\t' +
                itemID + '\t' +
                bidderAmount;
    }
}

public class Bids {
    private String id;
    private String userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  id + '\t' +
                userId + '\t' +
                bidderID + '\t' +
                bidderTime + '\t' +
                itemID + '\t' +
                bidderAmount;
    }
}

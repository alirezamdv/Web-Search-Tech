//Bids Table
class Bids {
    private String userId;
    private String bidderTime;
    private String itemID;
    private String bidderAmount;

    public Bids() {
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

    @Override
    public String toString() {
        return  userId + '\t' +
                bidderTime + '\t' +
                itemID + '\t' +
                bidderAmount;
    }
}

//Item Table
class Item {
    private String itemID;
    private String name;
    private String currently;
    private String firstBid;
    private String numberOfBids;
    private String buyPrice;
    private String geo_id;
    private String started;
    private String ends;
    private String userID;
    private String description;
    private String country;
    private String location;

    public Item() {
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrently() {
        return currently;
    }

    public void setCurrently(String currently) {
        this.currently = currently;
    }

    public String getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(String firstBid) {
        this.firstBid = firstBid;
    }

    public String getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(String numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getGeo_id() {
        return geo_id;
    }

    public void setGeo_id(String geo_id) {
        this.geo_id = geo_id;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return itemID + "\t" +
                name + "\t" +
                currently + "\t" +
                firstBid + "\t" +
                numberOfBids + "\t" +
                started + "\t" +
                ends + "\t" +
                buyPrice + "\t" +
                userID + "\t" +
                country + "\t" +
                location + "\t" +
                description;
    }
}

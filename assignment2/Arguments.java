public class Arguments {
    private String itemID;
    private String itemName;
    private String score;
    private String dist;
    private Double price;

    public Arguments(String itemID, String itemName, String score, String dist, Double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.score = score;
        this.dist = dist;
        this.price = price;
    }

    public String getScore() {
        return score;
    }

    public String getDist() {
        return dist;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return itemID +
                ", " + itemName +
                ", score: " + score +
                ", dist: " + dist +
                ", price: " + price;
    }
}

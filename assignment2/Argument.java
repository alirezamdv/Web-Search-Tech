public class Argument {
    private String itemID;
    private String itemName;
    private String score;
    private Double price;

    public Argument(String itemID, String itemName, String score, Double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.score = score;
        this.price = price;
    }

    public String getScore() {
        return score;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return itemID +
                ", " + itemName +
                ", score: " + score +
                ", price: " + price;
    }
}

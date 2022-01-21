//ItemCategory Table
class ItemCategory {
    private String itemID;
    private String itemCategory;

    public ItemCategory() {
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String toString() {
        return  itemID + '\t' +
                itemCategory;
    }
}
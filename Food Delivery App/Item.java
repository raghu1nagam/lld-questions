public class Item {
    private String name;
    private String price;
    private boolean isAvailable;
    Item(String name, String price, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public void viewItem() {
        if (this.isAvailable){
            System.out.println("Item: "+this.name + "  Cost: "+this.price);
        }
    }

    public void changePrice(String price) {
        this.price = price;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Long getPrice() {
        return Long.valueOf(this.price);
    }


}

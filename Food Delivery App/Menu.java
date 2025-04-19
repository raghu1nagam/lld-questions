import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<Item> items;
    Menu() {

    }
    void addItems(List<Item> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.addAll(items);
    }

    public List<Item> viewMenu() {
        System.out.println("Menu items::");
        items.forEach(Item::viewItem);
        return items;
    }

    public void changePrice(Item item,String price) {
        item.changePrice(price);
    }

    public void hideItems(List<Item> items) {
        items.forEach(item -> {
            item.setAvailable(false);
        });
    }

    public void unHideItems(List<Item> items) {
        items.forEach(item -> {
            item.setAvailable(true);
        });
    }

}

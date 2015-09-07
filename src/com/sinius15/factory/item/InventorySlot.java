package com.sinius15.factory.item;

import java.awt.*;

/**
 * Created by Sinius on 29-8-2015.
 */
public class InventorySlot {


    private int itemId;

    private int amount;

    public InventorySlot(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public void draw(Graphics2D g, int x, int y, int pixelSize){
        g.drawImage(this.getItem().getImage().getResizedImage(new Dimension(pixelSize, pixelSize)), x, y, null);
        g.setColor(Color.white);
        String line = this.getAmount() + "";
        int actual_width = g.getFontMetrics().stringWidth(line);
        g.drawString(line, x+pixelSize-actual_width, y+pixelSize);
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void subtractAmount(int amount) {
        this.amount-=amount;
    }

    public Item getItem() {
        return ItemProvider.getItem(itemId);
    }

    public boolean isEmpty(){
        return amount == 0;
    }

    @Override
    public String toString() {
        return "InventorySlot{" +
                "itemId=" + itemId +
                ", amount=" + amount +
                '}';
    }


}

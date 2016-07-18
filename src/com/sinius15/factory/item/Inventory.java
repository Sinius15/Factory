package com.sinius15.factory.item;

import com.sinius15.factory.engine.Input;

import java.awt.*;

/**
 * Created by Sinius on 29-8-2015.
 */
public class Inventory {

    private int slotHeight = 10;
    private int slotWidth = 4;

    public static final int pixelSize = 32;
    public static final int pixelPadding = 5;
    public static final int pixelFontSize = 20;

    public static final Color slotOutlineColor = new Color(150, 150, 150, 255);
    public static final Color unavailableColor = new Color(128, 128, 128, 150);

    InventorySlot[] slots = new InventorySlot[slotHeight*slotWidth];
    InventorySlot selected = new InventorySlot(-1, 0);

    public Inventory() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new InventorySlot(-1, 0);
        }
        slots[0].setItemId(ItemProvider.pickaxeItem.getId());
        slots[0].addAmount(5);
        slots[1].setItemId(ItemProvider.pickaxeItem.getId());
        slots[1].addAmount(3);
    }

    public void pickup(Item droppedItem, int amount) {
        int left = amount;
        //add to half filed slots
        for (InventorySlot slot : slots) {
            if (slot.getItemId() == droppedItem.getId()) {
                while (slot.getAmount() < droppedItem.getMaxStackSize() && left > 0){
                    slot.addAmount(1);
                    left--;
                }
            }
        }
        //add to empty slots
        for (InventorySlot slot : slots) {
            if (slot.isEmpty()) {
                while (slot.getAmount() < droppedItem.getMaxStackSize() && left > 0) {
                    slot.addAmount(1);
                    slot.setItemId(droppedItem.getId());
                    left--;
                }
            }
        }
    }

    public boolean hasFreeSlot(){
        for (InventorySlot slot : slots) {
            if(slot.isEmpty())
                return true;
        }
        return false;
    }

    public void draw(Graphics2D g){
        setInventoryFont(g);
        int i = 0;
        for(int x = 0; x < slotWidth; x++){
            for(int y = 0; y < slotHeight; y++){
                int xx = x*(pixelSize+pixelPadding)+pixelPadding;
                int yy = y*(pixelSize+pixelPadding)+pixelPadding;

                g.setColor(slotOutlineColor);
                g.drawRect(xx-1, yy-1, pixelSize+2, pixelSize+2);
                if(!slots[i].isEmpty()) {
                    slots[i].draw(g, xx, yy, pixelSize);
                }
                i++;
            }
        }
    }

    public static void setInventoryFont(Graphics2D g){
        g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), pixelFontSize));
    }

    public void tick(Input input) {
        Point mouse = input.getPoint();
        if(isMouseAboveInventory(mouse)) {
            if(input.isLeftPressed()){
                int clickedSlotNr = getInventorySlotAtPixel(mouse);
                InventorySlot clickedSlot = slots[clickedSlotNr];

                if(!clickedSlot.isEmpty() && !selected.isEmpty() &&
                        clickedSlot.getItemId() == selected.getItemId()) {
                    while (clickedSlot.getAmount() < clickedSlot.getItem().getMaxStackSize()
                            && selected.getAmount() > 0) {
                        clickedSlot.addAmount(1);
                        selected.subtractAmount(1);
                    }
                }else{
                    slots[clickedSlotNr] = selected;
                    selected = clickedSlot;
                }
            }
            if(input.isRightPressed()){
                int clickedSlotNr = getInventorySlotAtPixel(mouse);
                InventorySlot clickedSlot = slots[clickedSlotNr];
                System.out.println(clickedSlot.getAmount());
                int movingAmount = clickedSlot.getAmount()/2;
                if(clickedSlot.isEmpty() || !getSelectedSlot().isEmpty())
                    return;

                if(getSelectedSlot().spacesFree() < movingAmount) {
                    movingAmount = getSelectedSlot().spacesFree();
                }


                getSelectedSlot().addAmount(movingAmount);
                clickedSlot.subtractAmount(movingAmount);
                getSelectedSlot().setItemId(clickedSlot.getItemId());

            }
        }
    }

    public boolean isMouseAboveInventory(Point mouse){
        return mouse.x > pixelPadding && mouse.x < slotWidth*(pixelSize+pixelPadding)+pixelPadding &&
                mouse.y > pixelPadding && mouse.y < slotHeight*(pixelSize+pixelPadding)+pixelPadding;
    }

    public int getInventorySlotAtPixel(Point point){
        int xx = (point.x - pixelPadding) / (pixelSize + pixelPadding);
        int yy = (point.y - pixelPadding) / (pixelSize + pixelPadding);
        return xx*slotHeight+yy;
    }

    public boolean hasItemSelected(){
        return !selected.isEmpty();
    }

    public InventorySlot getSelectedSlot() {
        return selected;
    }

    public InventorySlot[] getSlots(){
        return slots;
    }

    public Item getSelectedItem(){
        if(hasItemSelected())
            return selected.getItem();
        return null;
    }


}

package com.sinius15.factory;

import com.sinius15.factory.engine.Input;
import com.sinius15.factory.item.Inventory;
import com.sinius15.factory.item.items.BasicItem;
import com.sinius15.factory.item.items.MinerItem;

import java.awt.*;

/**
 * Created by Sinius on 26-8-2015.
 */
public class Selector {

    private Point selectedTile = new Point(0, 0);

    public void draw(Graphics2D g, RenderInfo info, Input input, Inventory inventory){
        selectedTile = getSelectedTileLocation(info, input.getPoint());

        Inventory.setInventoryFont(g);

        if(inventory.hasItemSelected() && inventory.getSelectedItem() instanceof MinerItem){
            inventory.getSelectedSlot().draw(
                    g,
                    selectedTile.x * info.tileSize - info.xScroll,
                    selectedTile.y * info.tileSize - info.yScroll,
                    info.tileSize);
        }else if(inventory.hasItemSelected() && inventory.getSelectedItem() instanceof BasicItem){
            inventory.getSelectedSlot().draw(
                    g,
                    input.getPoint().x - Inventory.pixelSize / 2,
                    input.getPoint().y - Inventory.pixelSize / 2,
                    Inventory.pixelSize);

        }else{
            g.setColor(Color.white);
            g.drawRect(
                    selectedTile.x * info.tileSize - info.xScroll,
                    selectedTile.y * info.tileSize - info.yScroll,
                    info.tileSize,
                    info.tileSize);
        }

    }

    public Point getSelectedTileLocation(RenderInfo info, Point mouse){
        int offsetX = info.xScroll % info.tileSize;
        int offsetY = info.yScroll % info.tileSize;
        int tileX = (mouse.x+offsetX)/info.tileSize;
        int tileY = (mouse.y+offsetY)/info.tileSize;

        return new Point(tileX+info.xScroll/info.tileSize, tileY + info.yScroll/info.tileSize);
    }

    public Point getSelectedTile() {
        return selectedTile;
    }
}

package com.sinius15.factory.item.items;

import com.sinius15.factory.item.Inventory;
import com.sinius15.factory.item.Item;
import com.sinius15.factory.tile.Tile;
import com.sinius15.factory.world.World;

import java.awt.*;

/**
 * Created by Sinius on 30-8-2015.
 */
public class MinerItem extends Item {

    public MinerItem(int id, String name, String imageName, int maxStackSize) {
        super(id, name, imageName, maxStackSize);
    }

    @Override
    public boolean onWorldClick(Point clickedTile, World world, Inventory inventory) {
        Tile tile = world.getTile(clickedTile.x, clickedTile.y);
        if(tile.getDroppedItem() != null)
            inventory.pickup(tile.getDroppedItem(), 1);
        return false;
    }
}

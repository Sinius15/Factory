package com.sinius15.factory.item.items;

import com.sinius15.factory.item.Inventory;
import com.sinius15.factory.item.Item;
import com.sinius15.factory.world.World;

import java.awt.*;

/**
 * Created by Sinius on 30-8-2015.
 */
public class PlaceableItem extends Item {

    public PlaceableItem(int id, String name, String imageName, int maxStackSize) {
        super(id, name, imageName, maxStackSize);
    }

    @Override
    public boolean onWorldClick(Point clickedTile, World world, Inventory inventory) {
        return false;
    }


}

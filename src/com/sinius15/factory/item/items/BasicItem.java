package com.sinius15.factory.item.items;

import com.sinius15.factory.item.Inventory;
import com.sinius15.factory.item.Item;
import com.sinius15.factory.world.World;

import java.awt.*;

/**
 * Created by Sinius on 30-8-2015.
 */
public class BasicItem extends Item {

    public BasicItem(int id, String name, String imageName) {
        super(id, name, imageName, 100);
    }

    @Override
    public boolean onWorldClick(Point clickedTile, World world, Inventory inventory) {
        return false;
    }
}

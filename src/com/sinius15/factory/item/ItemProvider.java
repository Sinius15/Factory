package com.sinius15.factory.item;

import com.sinius15.factory.item.items.BasicItem;
import com.sinius15.factory.item.items.MinerItem;

/**
 * Created by Sinius on 29-8-2015.
 */
public class ItemProvider {

    public static final Item stoneItem = new BasicItem(0, "Stone", "ores/stone.png");
    public static final Item coalItem = new BasicItem(1, "Coal", "ores/coal.png");
    public static final Item diamondItem = new BasicItem(2, "Diamond", "ores/diamond.png");
    public static final Item goldItem = new BasicItem(3, "Gold", "ores/gold.png");
    public static final Item ironItem = new BasicItem(4, "Iron", "ores/Iron.png");
    public static final Item powerStoneItem = new BasicItem(5, "PowerStone", "ores/powerstone.png");
    public static final Item silverItem = new BasicItem(6, "Silver", "ores/silver.png");
    public static final Item woodItem = new BasicItem(7, "Wood", "ores/wood.png");

    public static final Item pickaxeItem = new MinerItem(8, "Pickaxe", "tools/pick_diamond.png", 10);

    private static final Item[] items = new Item[]{
            stoneItem,
            coalItem,
            diamondItem,
            goldItem,
            ironItem,
            powerStoneItem,
            silverItem,
            woodItem,
            pickaxeItem
    };

    public static Item getItem(int itemId) {
        if(itemId < 0 || itemId > items.length)
            return null;
        return items[itemId];
    }
}

package com.sinius15.factory.crafting;

/**
 * Created by Sinius on 1-9-2015.
 */
public class CraftingRecipeSpot {
    private final int itemId;
    private final int amount;

    public CraftingRecipeSpot(int itemId, Integer amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

}
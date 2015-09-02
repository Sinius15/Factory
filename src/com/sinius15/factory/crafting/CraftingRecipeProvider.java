package com.sinius15.factory.crafting;

import com.sinius15.factory.item.ItemProvider;


/**
 * Created by Sinius on 1-9-2015.
 */
public class CraftingRecipeProvider {

    public static final CraftingRecipe pickaxeReccipe = new CraftingRecipe(ItemProvider.pickaxeItem.getId(), 1,
            new CraftingRecipeSpot(ItemProvider.woodItem.getId(), 2),
            new CraftingRecipeSpot(ItemProvider.diamondItem.getId(), 3)
            );

    private static final CraftingRecipe[] recipes = new CraftingRecipe[]{
            pickaxeReccipe,
    };

    public static int amount() {
        return recipes.length;
    }

    public static CraftingRecipe getRecipe(int i) {
        if(i < 0 || i >= recipes.length)
            return null;
        return recipes[i];
    }

    public static CraftingRecipe[] getRecipes() {
        return recipes;
    }
}

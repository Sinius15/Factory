package com.sinius15.factory.crafting;

import com.sinius15.factory.RenderInfo;
import com.sinius15.factory.engine.Input;
import com.sinius15.factory.item.Inventory;
import static com.sinius15.factory.item.Inventory.pixelSize;
import com.sinius15.factory.item.Item;
import com.sinius15.factory.item.ItemProvider;

import java.awt.*;

/**
 * Created by Sinius on 1-9-2015.
 */
public class Crafter {

    private final int maxColumns = 10;

    // x,y = pixels left top. width height is cols and rows in blocks.
    private Rectangle drawingPlace = new Rectangle();

    private Rectangle drawingSpacePixel = new Rectangle();

    public void draw(Graphics2D g, RenderInfo info, Inventory inventory){
        Inventory.setInventoryFont(g);
        this.drawingPlace = calculateDrawingPlace(info, CraftingRecipeProvider.amount());
        this.drawingSpacePixel = calculateDrawingPlacePixel();

        CraftingRecipe[] recipes = CraftingRecipeProvider.getRecipes();
        for (int i = 0; i < recipes.length; i++) {
            CraftingRecipe recipe = recipes[i];

            Point cc = translateIToPixel(i);

            Item result = ItemProvider.getItem(recipe.getResultItemId());
            if (result == null)
                continue;
            g.drawImage(result.getImage(), cc.x, cc.y, pixelSize, pixelSize, null);
            if (!recipe.canCraft(inventory)) {
                g.setColor(Inventory.unavailableColor);
                g.fillRect(cc.x, cc.y, pixelSize, pixelSize);
            }
        }
//        g.setColor(Color.red);
//        g.drawRect(drawingSpacePixel.x, drawingSpacePixel.y, drawingSpacePixel.width, drawingSpacePixel.height);
    }


    public void tick(Input input, Inventory inventory) {
        if(this.drawingSpacePixel.contains(input.getPoint().x, input.getPoint().y)
                && input.isLeftPressed()){
            int recipeNr = translatePixelToI(input.getPoint().x, input.getPoint().y);
            CraftingRecipe recipe = CraftingRecipeProvider.getRecipe(recipeNr);
            if(recipe == null)
                return;
            recipe.tryToCraft(inventory);
        }
    }

    private Rectangle calculateDrawingPlace(RenderInfo info, int amountBlocks) {
        int cols = info.displaySize.width/(pixelSize+Inventory.pixelPadding);
        if(cols > maxColumns)
            cols = maxColumns;
        int rows = amountBlocks/cols + (amountBlocks % cols == 0 ? 0 : 1);
        return new Rectangle(
                Inventory.pixelPadding,
                info.displaySize.height-rows*(pixelSize+Inventory.pixelPadding),
                cols,
                rows);
    }

    private Point translateIToPixel(int i){
        int row = i / drawingPlace.width;
        int col = i % drawingPlace.width;

        return new Point(
                drawingPlace.x + col*(pixelSize+Inventory.pixelPadding),
                drawingPlace.y + row*(pixelSize+Inventory.pixelPadding)
        );
    }

    private int translatePixelToI(int x, int y){
        int col = (x-drawingSpacePixel.x)/ (pixelSize+Inventory.pixelPadding);
        int row = (y-drawingSpacePixel.y) / (pixelSize+Inventory.pixelPadding);

        return row * drawingPlace.width + col;
    }


    public Rectangle calculateDrawingPlacePixel(){
        return new Rectangle(
                drawingPlace.x,
                drawingPlace.y,
                drawingPlace.width*(pixelSize+Inventory.pixelPadding),
                drawingPlace.height*(pixelSize+Inventory.pixelPadding)
                );
    }
}

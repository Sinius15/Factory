package com.sinius15.factory.world;

import com.sinius15.factory.RenderInfo;
import com.sinius15.factory.Selector;
import com.sinius15.factory.engine.Input;
import com.sinius15.factory.item.Inventory;
import com.sinius15.factory.item.InventorySlot;
import com.sinius15.factory.item.Item;
import com.sinius15.factory.tile.Tile;
import com.sinius15.factory.tile.TileProvider;

import java.awt.*;

/**
 * Created by Sinius on 25-8-2015.
 */
public class World {

    private int[][] tiles = new int[100][100];

    public World() {}

    public void tick(Input input, Inventory inventory, Selector selector) {
        if(input.isLeftPressed()){
            Tile clickedtile = getTile(selector.getSelectedTile().x, selector.getSelectedTile().y);
            if(clickedtile == null)
                return;
            InventorySlot selectedSlot = inventory.getSelectedSlot();
            if(selectedSlot == null)
                return;
            Item selectedItem = selectedSlot.getItem();
            if(selectedItem == null)
                return;
            selectedItem.onWorldClick(selector.getSelectedTile(), this, inventory);
        }
    }

    public void draw(Graphics2D g, RenderInfo info){
        g.setColor(Color.black);
        g.fillRect(0, 0, info.displaySize.width, info.displaySize.height);

        int pixelPerTile = info.tileSize;

        //drawing tiles
        int startTileX = info.xScroll / pixelPerTile;
        int startTileY = info.yScroll / pixelPerTile;

        int endTileX = (int) (info.xScroll + info.displaySize.getWidth()) / pixelPerTile+1;
        int endTileY = (int) (info.yScroll + info.displaySize.getHeight())/ pixelPerTile+1;


        for(int x = startTileX; x < endTileX; x++){
            for(int y = startTileY; y < endTileY; y++){
                Tile tile = getTile(x, y);
                if(tile == null)
                    continue;
                g.drawImage(tile.getImage().getResizedImage(new Dimension(pixelPerTile, pixelPerTile)),
                        x * pixelPerTile - info.xScroll,
                        y * pixelPerTile - info.yScroll
                        , null);
//                g.drawString(x + ";" + y,
//                        x * pixelPerTile - info.xScroll,
//                        y * pixelPerTile - info.yScroll+10);
            }
        }
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return null;
        return TileProvider.getTile(tiles[x][y]);
    }

    public void setTile(int x, int y, int tileId){
        if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            System.err.println("Tried to set inpossible tile");
            return;
        }
        tiles[x][y] = tileId;
    }
}

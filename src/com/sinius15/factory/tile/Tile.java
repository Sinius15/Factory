package com.sinius15.factory.tile;

import com.sinius15.factory.FactoryGame;
import com.sinius15.factory.item.Item;
import com.sinius15.factory.util.ResizedImage;
import com.sinius15.factory.world.WorldGeneratorTileSetting;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Sinius on 25-8-2015.
 */
public class Tile {

    private final int id;

    private String name;
    private TileType type;
    private ResizedImage image;
    private WorldGeneratorTileSetting generatorSettings;
    private Item droppedItem;

    public Tile(int id, String name, String imageName, TileType type, WorldGeneratorTileSetting generatorSettings, Item droppedItem) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.generatorSettings = generatorSettings;
        this.droppedItem = droppedItem;
        try {
            this.image = new ResizedImage(ImageIO.read(FactoryGame.class.getResourceAsStream("tiles/" + imageName + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ResizedImage getImage(){
        return image;
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }

    public TileType getType() {
        return type;
    }

    public WorldGeneratorTileSetting getGeneratorSettings() {
        return generatorSettings;
    }

    public Item getDroppedItem() {
        return droppedItem;
    }

}

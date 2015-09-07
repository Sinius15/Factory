package com.sinius15.factory.item;

import com.sinius15.factory.FactoryGame;
import com.sinius15.factory.util.ResizedImage;
import com.sinius15.factory.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Sinius on 29-8-2015.
 */
public abstract class Item {

    private final int id;

    protected String name;
    protected ResizedImage image;
    protected int maxStackSize;

    public Item(int id, String name, String imageName, int maxStackSize) {
        this.name = name;
        this.id = id;
        this.maxStackSize = maxStackSize;
        try {
            this.image = new ResizedImage(ImageIO.read(FactoryGame.class.getResourceAsStream(imageName)));
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

    public int getMaxStackSize(){
        return maxStackSize;
    }

    public abstract boolean onWorldClick(Point clickedTile, World world, Inventory inventory);


}

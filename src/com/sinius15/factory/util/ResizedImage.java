package com.sinius15.factory.util;

import java.awt.*;
import java.awt.image.*;
import java.util.HashMap;

/**
 * Created by sijmen on 7-9-2015.
 */
public class ResizedImage extends BufferedImage{

    private HashMap<Dimension, BufferedImage> resizes = new HashMap<>();

    public ResizedImage(BufferedImage original) {
        super(original.getWidth(), original.getHeight(), original.getType());
        this.setData(original.getRaster());
    }

    public BufferedImage getResizedImage(Dimension size){
        if(resizes.containsKey(size))
            return resizes.get(size);
        BufferedImage newImg = new BufferedImage(size.width, size.height, this.getType());
        newImg.createGraphics().drawImage(this, 0, 0, size.width, size.height, null);
        resizes.put(size, newImg);
        System.out.println("resizeing" + resizes.size());
        return newImg;
    }
}

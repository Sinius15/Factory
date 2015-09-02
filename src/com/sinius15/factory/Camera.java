package com.sinius15.factory;

import com.sinius15.factory.engine.Display;
import com.sinius15.factory.engine.Input;
import com.sinius15.factory.tile.TileProvider;

import java.awt.*;

/**
 * Created by Sinius on 25-8-2015.
 */
public class Camera implements Input.WheelListener {

    //scroll in pixels
    private int xScroll = 0, yScroll = 0;

    private float zoomFactor = 1;
    private float zoomMax = 2f;
    private float zoomMin = 0.2f;
    private float zoomSpeed = 1.05f;
    private boolean zoomToMouse = true;
    private int scrollSpeed = 20;

    private Input input;
    private Display display;

    public Camera(Input input, Display display) {
        this.input = input;
        input.addWheelListener(this);
        this.display = display;
    }

    public void tick(){
        if(input.left.isPressed())
            xScroll-=scrollSpeed;
        if(input.right.isPressed())
            xScroll+=scrollSpeed;
        if(input.up.isPressed())
            yScroll-=scrollSpeed;
        if(input.down.isPressed())
            yScroll+=scrollSpeed;
    }

    @Override
    public void onScroll(int change) {
        float actualZoomFactor = zoomFactor;
        zoomFactor = Math.max(Math.min(zoomFactor / (float)Math.pow(zoomSpeed, change), zoomMax), zoomMin);
        actualZoomFactor = zoomFactor / actualZoomFactor;

        int x, y;
        if(zoomToMouse){
            x = input.getPoint().x;
            y = input.getPoint().y;
        }else{
            x = display.getWidth()/2;
            y = display.getHeight()/2;
        }

        int lx = xScroll + x;
        int ly = yScroll + y;

        float lxa = lx*actualZoomFactor;
        float lya = ly*actualZoomFactor;

        xScroll = (int) (lxa-x);
        yScroll = (int) (lya-y);

    }

    public RenderInfo getRenderInformation(Dimension displaySize) {
        int pixelPerTile = (int) (TileProvider.IMAGE_SIZE * zoomFactor);
        return new RenderInfo(xScroll, yScroll, zoomFactor, displaySize, pixelPerTile);
    }
}

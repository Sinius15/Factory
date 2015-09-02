package com.sinius15.factory;

import java.awt.*;

/**
 * Created by Sinius on 26-8-2015.
 */
public class RenderInfo {

    public final int xScroll, yScroll;
    public final float zoomFactor;
    public final Dimension displaySize;
    public final int tileSize;

    public RenderInfo(int xScroll, int yScroll, float zoomFactor, Dimension displaySize, int tileSize) {
        this.xScroll = xScroll;
        this.yScroll = yScroll;
        this.zoomFactor = zoomFactor;
        this.displaySize = displaySize;
        this.tileSize = tileSize;
    }
}

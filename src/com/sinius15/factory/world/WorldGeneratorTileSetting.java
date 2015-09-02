package com.sinius15.factory.world;

/**
 * Created by Sinius on 29-8-2015.
 */
public class WorldGeneratorTileSetting {

    private int frequencyMin, frequencyMax, sizeMin, sizeMax, parentTileId;

    public WorldGeneratorTileSetting(int frequencyMin, int frequencyMax, int sizeMin, int sizeMax, int parentTileId) {
        this.frequencyMin = frequencyMin;
        this.frequencyMax = frequencyMax;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
        this.parentTileId = parentTileId;
    }

    public int getFrequencyMin() {
        return frequencyMin;
    }

    public int getFrequencyMax() {
        return frequencyMax;
    }

    public int getSizeMin() {
        return sizeMin;
    }

    public int getSizeMax() {
        return sizeMax;
    }

    public int getParentTileId() {
        return parentTileId;
    }
}

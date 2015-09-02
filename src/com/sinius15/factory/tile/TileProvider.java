package com.sinius15.factory.tile;

import com.sinius15.factory.item.ItemProvider;
import com.sinius15.factory.world.WorldGeneratorTileSetting;

import java.util.Random;

/**
 * Created by Sinius on 29-8-2015.
 */
public class TileProvider {

    public static final Tile grassTile =       new Tile(0, "Grass", "grass",                   TileType.BACKGROUND, null, null);
    public static final Tile dirtTile =        new Tile(1, "Dirt", "dirt",                     TileType.OVERLAY, new WorldGeneratorTileSetting(3, 6, 700, 900, grassTile.getId()), null);
    public static final Tile stoneTile =       new Tile(2, "Stone", "stone",                   TileType.OVERLAY, new WorldGeneratorTileSetting(7, 9, 100, 200, grassTile.getId()), ItemProvider.stoneItem);
    public static final Tile coalTile =        new Tile(3, "Coal", "stone_coal",               TileType.ORE, new WorldGeneratorTileSetting(8, 12, 8, 12, stoneTile.getId()), ItemProvider.coalItem);
    public static final Tile diamondTile =     new Tile(4, "Diamond", "stone_diamond",         TileType.ORE, new WorldGeneratorTileSetting(1, 3, 1, 3, coalTile.getId()), ItemProvider.diamondItem);
    public static final Tile goldTile =        new Tile(5, "Gold", "stone_gold",               TileType.ORE, new WorldGeneratorTileSetting(3, 5, 4, 6, stoneTile.getId()), ItemProvider.goldItem);
    public static final Tile ironTile =        new Tile(6, "Iron", "stone_iron",               TileType.ORE, new WorldGeneratorTileSetting(5, 8, 10, 30, stoneTile.getId()), ItemProvider.ironItem);
    public static final Tile powerStoneTile =  new Tile(7, "PowerStone", "stone_powerstone",   TileType.ORE, new WorldGeneratorTileSetting(2, 4, 2, 4, ironTile.getId()), ItemProvider.powerStoneItem);
    public static final Tile silverTile =      new Tile(8, "Silver", "stone_silver",           TileType.ORE, new WorldGeneratorTileSetting(2, 6, 2, 3, stoneTile.getId()), ItemProvider.silverItem);
    public static final Tile trunk =           new Tile(9, "Trunk", "trunk",                   TileType.ORE, new WorldGeneratorTileSetting(10, 30, 3, 4, grassTile.getId()), ItemProvider.woodItem);

    private static final Tile[] availableTiles = new Tile[]{
            grassTile,
            dirtTile,
            stoneTile,
            coalTile,
            diamondTile,
            goldTile,
            ironTile,
            powerStoneTile,
            silverTile,
            trunk
//            new Tile(9, "Water", "water", 2, 60, -1),
//            new Tile(10, "Lava", "lava", 3, 30, -1),

    };

    private static Random random = new Random();

    public static final int IMAGE_SIZE = 128;

    public static Tile randomTile(){
        return availableTiles[random.nextInt(availableTiles.length)];
    }
    public static Tile getTile(int id){
        return availableTiles[id];
    }
    public static Tile[] getAllTiles(){
        return availableTiles;
    }


}

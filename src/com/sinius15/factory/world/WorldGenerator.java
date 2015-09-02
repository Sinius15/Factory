package com.sinius15.factory.world;

import com.sinius15.factory.tile.Tile;
import com.sinius15.factory.tile.TileProvider;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sinius on 27-8-2015.
 */
public class WorldGenerator {

    private static Random random = new Random();

    public static World generateWorld(){
        World world = new World();

        for(Tile tile : TileProvider.getAllTiles()){
            WorldGeneratorTileSetting tileSetting = tile.getGeneratorSettings();
            if(tileSetting == null)
                continue;
            int frequency = randomBetween(tileSetting.getFrequencyMin(), tileSetting.getFrequencyMax());

            for(int i = 0; i < frequency; i++){
                int size = randomBetween(tileSetting.getSizeMin(), tileSetting.getSizeMax());

                Point startLocation;
                Tile startTile;
                do{
                    startLocation = new Point(
                            random.nextInt(world.getWidth()),
                            random.nextInt(world.getHeight()));
                    startTile = world.getTile(startLocation.x, startLocation.y);
                    //ores only spawn on non background things
                }while(tileSetting.getParentTileId() != startTile.getId());

                ArrayList<Point> generatedPoints = new ArrayList<>();
                generatedPoints.add(startLocation);
                world.setTile(startLocation.x, startLocation.y, tile.getId());

                do{
                    Point nextPoint = getRandomPointAround(world, generatedPoints, tile.getId());
                    if(nextPoint == null)
                        break;
                    world.setTile(nextPoint.x, nextPoint.y, tile.getId());
                    generatedPoints.add(nextPoint);
                }while(generatedPoints.size() <= size);
            }
        }



        return world;
    }

    private static Point getRandomPointAround(World world, ArrayList<Point> starting, int tileExclude){
        ArrayList<Point> points = new ArrayList<>();
        for(Point p : starting) {
            points.addAll(getPointsAroundExluding(p, world, tileExclude));
        }
        if(points.size() == 0)
            return null;
        return points.get(
                random.nextInt(
                        points.size()));
    }
    
    private static ArrayList<Point> getPointsAroundExluding(Point p, World world, int tileExclude){
        ArrayList<Point> points =new ArrayList<>();
        if(world.getTile(p.x + 1, p.y) != null && world.getTile(p.x + 1, p.y).getId() != tileExclude)
            points.add(new Point(p.x+1, p.y));
        if(world.getTile(p.x - 1, p.y) != null && world.getTile(p.x - 1, p.y).getId() != tileExclude)
            points.add(new Point(p.x-1, p.y));
        if(world.getTile(p.x, p.y + 1) != null && world.getTile(p.x, p.y + 1).getId() != tileExclude)
            points.add(new Point(p.x, p.y+1));
        if(world.getTile(p.x, p.y - 1) != null && world.getTile(p.x, p.y - 1).getId() != tileExclude)
            points.add(new Point(p.x, p.y-1));
        return points;
    }

    private static int randomBetween(int low, int high){
        return random.nextInt(high-low) + low;
    }

}

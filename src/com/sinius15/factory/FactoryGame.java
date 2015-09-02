package com.sinius15.factory;

import com.sinius15.factory.crafting.Crafter;
import com.sinius15.factory.engine.Display;
import com.sinius15.factory.engine.DisplayImplementor;
import com.sinius15.factory.engine.Input;
import com.sinius15.factory.item.Inventory;
import com.sinius15.factory.world.World;
import com.sinius15.factory.world.WorldGenerator;

import java.awt.*;

/**
 * Created by Sinius on 25-8-2015.
 */
public class FactoryGame implements DisplayImplementor {

    private Display display;
    private World world;
    private Input input;
    private Camera camera;
    private Selector selector;
    private Inventory inventory;
    private Crafter crafter;

    public FactoryGame() {
        this.display = new Display("Factory");
        this.display.setRenderer(this);

        this.input = display.getInput();

        this.world = WorldGenerator.generateWorld();
        this.camera = new Camera(this.input, this.display);
        this.selector = new Selector();
        this.inventory = new Inventory();
        this.crafter = new Crafter();

        this.display.start();
    }

    public static void main(String[] args) {
        new FactoryGame();
    }


    @Override
    public void render(Graphics2D g, Dimension size) {
        RenderInfo info = camera.getRenderInformation(size);
        world.draw(g, info);
        selector.draw(g, info, input, inventory);
        inventory.draw(g);
        crafter.draw(g, info, inventory);
    }

    @Override
    public void tick() {
        input.tick();
        inventory.tick(input);
        crafter.tick(input, inventory);
        world.tick(input, inventory, selector);
        camera.tick();

    }
}

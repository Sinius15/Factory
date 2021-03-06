package com.sinius15.factory.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;

/**
 * @author davidot
 */
public class Display extends Canvas implements Runnable {

    public static final int SLEEPTIME = 2;

    /**
     * The target amount of ticks per second
     */
    public static final int TARGET_TICKS = 60;

    /**
     * The amount of nanoseconds one tick can take
     */
    public static final double NS_TICKS = 1000000000.0 / TARGET_TICKS;

    private final JFrame frame;

    private boolean isRunning;

    private Thread mainThread;

    private String title;

    private DisplayImplementor renderer;
    private Input input;

    private int width;
    private int height;

    public Display(String title) {
        this.title = title;
        this.frame = new JFrame(title);


        //Make the frame
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.setResizable(true);


        //Make sure the frame is packed
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    frame.pack();
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }

        frame.setLocationRelativeTo(null);
        input = new Input(this);
        frame.setVisible(true);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeFrame(e.getComponent().getSize());
            }
        });

        frame.setSize(1080, 720);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        int frames = 0;
        int ticks = 0;
        long lastTimer = System.currentTimeMillis();

        while (isRunning) {
            boolean shouldRender = false;
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / NS_TICKS;
            lastTime = now;
            if (unprocessed > TARGET_TICKS * 5) {
                double ticksLeft = TARGET_TICKS * 5;
                System.out.println("Skipping " + (int) (unprocessed - ticksLeft) +
                        " ticks, is the system overloaded?");
                unprocessed = ticksLeft;
            }
            while (unprocessed >= 1) {
                renderer.tick();
                ticks++;
                unprocessed--;
                shouldRender = true;
            }

            if (shouldRender) {
                frames++;
                render();
            }

            try {
                Thread.sleep(SLEEPTIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000;
                frame.setTitle(title + " | " + frames + " fps | " + ticks + " ticks");
                frames = 0;
                ticks = 0;
            }
        }
    }

    /**
     * Start the display
     */
    public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        System.out.println("Starting main thread");
        mainThread = new Thread(this, "Tick Thread");
        mainThread.start();
    }

    /**
     * Stops the display
     */
    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.join();
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void render() {
        BufferStrategy buffer = getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(2);
            requestFocus();
            return;
        }
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        renderer.render(g, new Dimension(width, height));
        g.dispose();
        buffer.show();
    }

    private void resizeFrame(Dimension size) {
        this.width = size.width;
        this.height = size.height;
    }

    public void setRenderer(DisplayImplementor renderer) {
        this.renderer = renderer;
    }

    public DisplayImplementor getRenderer() {
        return renderer;
    }

    public Input getInput() {
        return input;
    }
}
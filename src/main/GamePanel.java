package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;

    public final int maxScreenC = 16;
    public final int maxScreenR = 12;
    public final int screenWidth = maxScreenC * tileSize;
    public final int screenHeight = maxScreenR * tileSize;

    int FPS = 60;

    Thread gameThread;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setVisible(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >=1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        player.draw(g2d);
        g2d.dispose();
    }


}


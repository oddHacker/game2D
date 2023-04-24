package entity;
import main.KeyHandler;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 200;
        y = 100;
        playerSpeed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true) {
            direction = "up";
            y -= playerSpeed;
        } else if (keyH.downPressed == true){
            direction = "down";
            y += playerSpeed;
        } else if (keyH.leftPressed == true) {
            direction = "left";
            x -= playerSpeed;
        }   else if (keyH.rightPressed == true) {
            direction = "right";
            x += playerSpeed;
        }
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            spriteCounter++;
            if (spriteCounter > 10){
                if (spriteNumber == 1)
                    spriteNumber = 2;
                else
                    spriteNumber = 1;
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNumber == 1)
                    image = up1;
                else
                    image = up2;
                break;
            case "down":
                if(spriteNumber == 1)
                    image = down1;
                else
                    image = down2;
                break;
            case "left":
                if(spriteNumber == 1)
                    image = left1;
                else
                    image = left2;
                break;
            case "right":
                if(spriteNumber == 1)
                    image = right1;
                else
                    image = right2;
                break;

        }
        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}

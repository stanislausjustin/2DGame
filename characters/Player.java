package characters;

import main.GamePanel;
import main.KeyInputs;
import main.Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyInputs keyI;

    /*
    screens x and y are basically the map and they dont change. they're fixed, so what is constantly changing
    is the player's character position and camera view
     */
    public final int screenX;
    public final int screenY;
    public int KeyFound = 0;

    public Player(GamePanel gp, KeyInputs keyI) {

        super(gp); //calling the constructor of the superclass of this class


        this.keyI = keyI;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 23, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        worldX = gp.tileSize * 18;
        worldY = gp.tileSize * 5;
        speed = 4;
        direction = "right"; //initial direction

        // player stats
        maxHealth = 6;
        health = maxHealth; //current health = full
    }

    public void getPlayerImage() {

        up1 = setup("player/player_up_1");
        up2 = setup("player/player_up_2");
        up3 = setup("player/player_up_3");
        down1 = setup("player/player_down_1");
        down2 = setup("player/player_down_2");
        down3 = setup("player/player_down_3");
        left1 = setup("player/player_left_1");
        left2 = setup("player/player_left_2");
        left3 = setup("player/player_left_3");
        right1 = setup("player/player_right_1");
        right2 = setup("player/player_right_2");
        right3 = setup("player/player_right_3");

    }

    public void update() {

        if(keyI.keyUp == true || keyI.keyDown == true ||
                keyI.keyLeft == true || keyI.keyRight == true) {

            if (keyI.keyUp == true) { //y values increase downwards
                direction = "up"; //posisi direction playerny
            }
            if (keyI.keyDown == true) {
                direction = "down";
            }
            if (keyI.keyLeft == true) { //x values increase to the right
                direction = "left";
            }
            if (keyI.keyRight == true) {
                direction = "right";
            }

            //checks tile collision
            collisionOn = false;
            gp.checker.checkTile(this);

            //checks object collision
            int objIndex = gp.checker.ObjectChecker(this, true);
            pickUpObject(objIndex);

            //checks NPC collision
            int npcIndex = gp.checker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //checks events
            gp.event.checkEvent();

            //aft check npc and event
            gp.keyI.keyEnter = false;

            //if collision = false, player can move
            if(collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed; //downwards naik
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed; //right naik
                        break;
                }
            }

            sprite++;
            if(sprite > 8) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                sprite = 0;
            }
        }

    }

    public void pickUpObject(int i) {

        if (i != 999) { //if index isn't 999 then it has touched an object
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playEffects(1);
                    KeyFound++; // +1 key tiap collide with a key
                    gp.obj[i] = null; //removes the key abis di pick up
                    gp.ui.showMessage("key +1");
                    break;
                case "Door", "Door2":
                    if (KeyFound > 0) {
                        gp.playEffects(3);
                        gp.obj[i] = null;
                        KeyFound--; //abis pke key ke door 1 key ilang
                        gp.ui.showMessage("MEOW");
                    } else { gp.ui.showMessage("Find a key first stupid");}
                    break;
                case "Flask":
                    gp.playEffects(2);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("ngeouuuuuu");
                    break;
                case "Chest":
                    gp.ui.GameOver = true;
                    gp.stopLaufey();
                    gp.playEffects(4);
                    break;
            }
        }
    }

    public void interactNPC(int i) {

        if(i != 999) {

            if(gp.keyI.keyEnter == true) {
                gp.GameState = gp.DialogueState;
                gp.npc[i].speech();
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 3) {
                    image = right3;
                }
                break;
        }

        g2.drawImage (image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}

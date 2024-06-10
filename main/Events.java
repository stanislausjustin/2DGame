package main;

import java.awt.*;

public class Events {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public Events (GamePanel gp) { //constructor
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {

        if(hit(18, 11, "down") == true) {
            //event happens
            damagePit(gp.DialogueState);
        }
        if(hit(18, 10, "right") == true) {
            //event happens
            damagePit(gp.DialogueState);
        }
        if(hit(35, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }
        if(hit(34, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }
        if(hit(32, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }
        if(hit(33, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }
        if(hit(36, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }
        if(hit(37, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }
        if(hit(38, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }
        if(hit(39, 13, "up") == true) {
            healingPool(gp.DialogueState);
        }

    }
    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        //checks if player collides with eventRect's solid area
        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
    public void damagePit(int GameState) {

        gp.GameState = GameState;
        gp.ui.DialogueText = "You fell into a pit";
        gp.player.health -= 1;
    }
    public void healingPool(int GameState) {
        if(gp.keyI.keyEnter == true) {
            gp.GameState = GameState;
            gp.ui.DialogueText = "no longer stinky";
            gp.player.health = gp.player.maxHealth;
        }
    }
}

package main;

import java.awt.*;
import java.awt.image.BufferedImage;

import object.Health;
import object.Key;
import object.ParentObjects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font ariel, ariel2, tmr, tmr2;
    BufferedImage keyImage, full, half, ded;
    public boolean messageOn = false;
    public String message = "";
    int messagetimer = 0;
    public boolean GameOver = false;
    public String DialogueText = "";

    public UI (GamePanel gp) {
        this.gp = gp;

        ariel = new Font("Arial", Font.PLAIN, 80);
        ariel2 = new Font("Arial", Font.PLAIN, 25);
        tmr = new Font("Times New Roman", Font.PLAIN, 30);
        tmr2 = new Font("Times New Roman", Font.BOLD, 60);
        Key key = new Key(gp);
        keyImage = key.image;

        ParentObjects heart = new Health(gp);
        full = heart.image;
        half = heart.image2;
        ded = heart.image3;
    }

    public void showMessage (String text) {

        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2) {
        this.g2 = g2;

        if (GameOver == true) {

            g2.setFont(tmr);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "ggs you beat zhandos";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2;
            g2.drawString(text, x, y);

            g2.setFont(tmr2);
            g2.setColor(Color.WHITE);
            text = "100 for scicomp";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);

            g2.drawString(text, x, y);

            gp.gameThread = null; //stops the thread

        } else {
            g2.setFont(tmr);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.KeyFound, 74, 57);

            // Message
            if (messageOn == true) {

                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messagetimer++;

                if (messagetimer > 120) { //120 frames = 2 seconds
                    messagetimer = 0;
                    messageOn = false;
                }
            }
            //play state
            if(gp.GameState == gp.PlayState) {
                drawPlayerLife();
            }
            //pause state
            if(gp.GameState == gp.PauseState) {
                drawPlayerLife();
                drawPauseScreen();
            }
            //dialogue state
            if(gp.GameState == gp.DialogueState) {
                drawPlayerLife();
                drawDialogueScreen();
            }
        }
    }

    public void drawPlayerLife() {

        int x = gp.tileSize*12;
        int y = gp.tileSize/2;
        int i = 0;

        //draws full health
        while(i < gp.player.maxHealth/2) {
            g2.drawImage(ded, x, y, null);
            i++;
            x += gp.tileSize; //draws 0 health pic
        }

        //resets
        x = gp.tileSize*12;
        y = gp.tileSize/2;
        i = 0;

        //draws current health
        while(i < gp.player.health) {
            g2.drawImage(half, x, y, null);
            i++;
            if(i < gp.player.health) {
                g2.drawImage(full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }
    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getCenter(text) - 90;
        int y = gp.screenHeight / 2 + 30;

        g2.setFont(ariel);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
    }
    public int getCenter(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
    public void drawDialogueScreen() {

        //window parameters
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(ariel2);
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : DialogueText.split("\n")) {
            g2.drawString(line, x, y);
            y += 40; //splitting the text thats inside the dialogue
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color (0, 0,0 , 205); //a = transparency or opacity > increases by decreasing the number
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);
    }
}

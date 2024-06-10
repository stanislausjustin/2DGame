package main;

import javax.swing.*;
import java.awt.*;

import characters.Entity;
import characters.Player;
import object.ParentObjects;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; /* 16x16 tile*/
    final int scale = 3; //ini spya jd 48x48 so it fits with the screen's res better

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; //16 columns
    public final int maxScreenRow = 12; //12 rows

    /*ini jd 16 samping 12 bwh so 4 by 3 ratio*/
    public final int screenWidth = tileSize * maxScreenCol; //768px
    public final int screenHeight = tileSize * maxScreenRow; //576px


    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyInputs keyI = new KeyInputs(this);
    SoundEffects sound = new SoundEffects();
    SoundEffects music = new SoundEffects();
    /*kl mau repeat a process like drawing a screen 60x a second,
    thread is useful cuz the program operates more efficiently*/
    public Collisions checker = new Collisions(this);
    public Assets AssetSetter = new Assets(this);
    public UI ui = new UI(this);
    public Events event = new Events(this);
    Thread gameThread;
    public Player player = new Player(this,keyI);
    public ParentObjects obj[] = new ParentObjects[10]; //size 10 -> 10 slots for objects
    public Entity npc[] = new Entity[10];


    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //Game state
    public int GameState;
    public final int PlayState = 1;
    public final int PauseState = 2;
    public final int DialogueState = 3;

    public GamePanel() { //constructor of the gamePanel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //double buffering helps improve rendering performance
        this.addKeyListener(keyI);
        this.setFocusable(true); //so that it can be focused to receive key inputs
    }

    public void setupGame() {

        AssetSetter.setObject();
        AssetSetter.NPCS();
        playLaufey(0);
        GameState = PlayState;

    }

    public void startGameThread() {
        gameThread = new Thread(this); //GamePanel class is passed to this thread constructor
        gameThread.start(); //automatically call the run method for the game loop
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long Time = System.nanoTime();
        long currentTime;
        long timer = 0;
        int FPSCount = 0;

        while (gameThread != null) { //as long as gameThread is not null the process written here is repeated

            currentTime = System.nanoTime();
            delta += (currentTime - Time) / drawInterval;
            timer += (currentTime - Time);
            Time = currentTime;

            if (delta >= 1) {
                //update information like character's positions
                update();
                //draw the screen with the updated information
                repaint();
                delta--;
                FPSCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + FPSCount);;
                FPSCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {

        if(GameState == PlayState) {
            player.update();
            //NPC
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
        }
        if(GameState == PauseState) {
            //nothing = paused
        }

        }

    }

    public void paintComponent(Graphics g) { //paintComponent is a built-in method to draw things in JPanel

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // debug
        long drawStart = 0;
        if (keyI.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //draws tile
        tileM.draw(g2);

        //draws object
        for(int i = 0; i  < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //draws npcs
        for(int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        //draws player
        player.draw(g2);

        //draws the ui
        ui.draw(g2);

        //debug
        if (keyI.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time:"+passed);
        }

        g2.dispose(); //Dispose of this graphics context and release any system resources that it is using
    }

    public void playLaufey(int i) {

        music.setSound(i);
        music.playSound();
        music.loop();
    }
    public void stopLaufey() {

        music.stop();
    }
    public void playEffects(int i) {

        sound.setSound(i);
        sound.playSound(); //gausa loop lah y
    }
}

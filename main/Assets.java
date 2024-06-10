package main;

import characters.Gibus;
import object.*;

public class Assets {

    GamePanel gp;

    public Assets(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Key(gp);
        gp.obj[0].worldX = 35 * gp.tileSize;
        gp.obj[0].worldY = 13 * gp.tileSize;

        gp.obj[1] = new Key(gp);
        gp.obj[1].worldX = 41 * gp.tileSize;
        gp.obj[1].worldY = 45 * gp.tileSize;

        gp.obj[3] = new Door(gp);
        gp.obj[3].worldX = 37 * gp.tileSize;
        gp.obj[3].worldY = 4 * gp.tileSize;

        gp.obj[4] = new Door2(gp);
        gp.obj[4].worldX = 38 * gp.tileSize;
        gp.obj[4].worldY = 4 * gp.tileSize;


        gp.obj[6] = new Chest(gp);
        gp.obj[6].worldX = 38 * gp.tileSize;
        gp.obj[6].worldY = 0 * gp.tileSize;

        gp.obj[7] = new Flask(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;

    }

    public void NPCS() { //sets the npcs

        gp.npc[0] = new Gibus(gp);
        gp.npc[0].worldX = gp.tileSize*31;
        gp.npc[0].worldY = gp.tileSize*7;
    }
}

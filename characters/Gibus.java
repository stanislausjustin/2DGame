package characters;

import main.GamePanel;
import java.awt.*;
import java.util.Random;


public class Gibus extends Entity {

    public Gibus (GamePanel gp) {

        super(gp);

        direction = "down";
        speed = 1;
        solidArea = new Rectangle(8, 16, 48, 48);

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("npc/gib_up_1");
        up2 = setup("npc/gib_up_2");
        up3 = setup("npc/gib_up_3");
        down1 = setup("npc/gib_down_1");
        down2 = setup("npc/gib_down_2");
        down3 = setup("npc/gib_down_3");
        left1 = setup("npc/gib_left_1");
        left2 = setup("npc/gib_left_2");
        left3 = setup("npc/gib_left_3");
        right1 = setup("npc/gib_right_1");
        right2 = setup("npc/gib_right_2");
        right3 = setup("npc/gib_right_3");

    }

    public void setDialogue() {
        dialogues[0] = "Hey there! My name is Gibus.";
        dialogues[1] = "Long story short, I experienced a \ntraumatic event where I almost drowned.";
        dialogues[2] = "I can't swim :(";
        dialogues[3] = "Help me get that key will you?";

    }
    public void setAction() {

        actionLoop++;

        if(actionLoop == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //chooses a random number from 1-100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "right";
            }
            if(i > 75 && i <= 100) {
                direction = "left";
            }

            actionLoop = 0; //aft it picks a direction, it won't change for 120 frames or 2 secs
        }
    }

    public void speech() {

        super.speech();

    }
}
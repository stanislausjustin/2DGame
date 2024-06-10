package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door2 extends ParentObjects {
    GamePanel gp;
    public Door2(GamePanel gp) {

        this.gp = gp;

        name = "Door2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_2.png"));
            Utility.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }
}

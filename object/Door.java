package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends ParentObjects {
    GamePanel gp;
    public Door(GamePanel gp) {

        this.gp = gp;

        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_1.png"));
            Utility.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }
}

package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends ParentObjects{
    GamePanel gp;
    public Chest(GamePanel gp) {

        this.gp = gp;

        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            Utility.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

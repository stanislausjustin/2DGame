package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Flask extends ParentObjects{
    GamePanel gp;
    public Flask(GamePanel gp) {

        this.gp = gp;

        name = "Flask";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/flasks.png"));
            Utility.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }

}

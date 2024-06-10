package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Health extends ParentObjects {
    GamePanel gp;
    public Health(GamePanel gp) {

        this.gp = gp;

        name = "Health";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/ded.png"));

            image = Utility.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = Utility.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = Utility.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}

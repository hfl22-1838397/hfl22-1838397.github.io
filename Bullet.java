import javax.swing.*;
import java.awt.*;

public class Bullet extends Ship {

    public Bullet(int x) {
        y = 415;
        this.x = x;
        ImageIcon bullet = new ImageIcon("src/pshot.png");
        image = bullet.getImage();
    }

    public Image getImage() {
        return image;
    }

    public void move() {
        y -= 40;
    }

}

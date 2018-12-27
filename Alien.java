import javax.swing.*;
import java.awt.*;

public class Alien {
    private Image image;
    private int x;
    private int y;
    private int w = 25;
    private int h = 25;

    public Alien() {
        ImageIcon alien = new ImageIcon("src/alien.png");
        image = alien.getImage();

    }

    public Image getImage() {
        return image;
    }
}

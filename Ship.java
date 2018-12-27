import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Ship {
    int x;
    int y;
    Image image;
    private ArrayList<Bullet> bulletList;

    public Ship() {
        x = 220;
        y = 400;
        ImageIcon ship = new ImageIcon("src/player.png");
        image = ship.getImage();
        bulletList = new ArrayList<>();
    }

    public void move(int speed){
        x += speed;
    }

    public void fire() {
        bulletList.add(new Bullet(x));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }

    public Image getImage() {
        return image;
    }

}

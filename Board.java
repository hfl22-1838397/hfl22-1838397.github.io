import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;


public class Board  extends JPanel implements Runnable, MouseListener
{
    boolean ingame = true;
    private Dimension d;
    int BOARD_WIDTH=500;
    int BOARD_HEIGHT=500;
    int x = 0;
    BufferedImage img;
    String message = "Click Board to Start";
    private Thread animator;
    Boolean row1 = true;
    int Rownumber1 = 3;
    int SpaceY1= 60;
    Boolean row2 = true;
    int SpaceY2= 60;
    int Rownumber2 = 3;
    Boolean row3 = true;
    int SpaceY3= 60;
    int Rownumber3 = 3;
    Boolean row4 = true;
    int SpaceY4= 60;
    int Rownumber4 = 3;
    Boolean row5 = true;
    int SpaceY5= 60;
    int Rownumber5 = 3;
    Boolean row6 = true;
    int SpaceY6= 60;
    int Rownumber6 = 3;
    Boolean row7 = true;
    int SpaceY7= 60;
    int Rownumber7 = 3;
    Boolean row8 = true;
    int SpaceY8= 60;
    int Rownumber8 = 3;
    Boolean row9 = true;
    int SpaceY9= 60;
    int Rownumber9 = 3;
    Boolean row10 = true;
    int SpaceY10= 60;
    int Rownumber10 = 3;
    int Ay= 0;
    int Ax = 1;
    int ShotX =0;
    int ShotY = 0;
    int score = 0;
    int move =10;
    int shipSpeed = 0;

    private Ship ship = new Ship();


    public Board()
    {
        addKeyListener(new TAdapter());
        addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
           /*
             try {
                img = ImageIO.read(this.getClass().getResource("mount.jpg"));
            } catch (IOException e) {
                 System.out.println("Image could not be read");
            // System.exit(1);
            }
            */
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
        setDoubleBuffered(true);
    }
    public void paint(Graphics g)
    {
        String message = "Score"+score;
        if(score==20){
            ingame=false;
            message = "You Win!";
        }
        if(Ay==350){
            ingame=false;
            message = "You lose";
        }
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
//g.fillOval(x,y,r,r);
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(message, 10, d.height-60);

        if (ingame) {
            ship.move(shipSpeed);
            g.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
            for(int i = 0; i < ship.getBulletList().size(); i++){
                Bullet tempBullet = ship.getBulletList().get(i);
                if(tempBullet.getY() < -10) {
                    ship.getBulletList().remove(i);
                }
                tempBullet.move();
            }
            for(Bullet bullet : ship.getBulletList()) {
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
                ShotX =bullet.getX();
                ShotY =bullet.getY();
                if (ShotY< 0){
                    ShotX = 0;
                    ShotY= 0;
                }
            }
            if (Ax > 200) {
                move = -move;
                Ay = Ay + 50;
            }
            if (Ax < -1){
                move = -move;
                Ay = Ay +50;
            }
            Ax= Ax +move;

            if (row1) {
                if (Rownumber1 > 1) {
                    g.drawImage(new Alien().getImage(), Ax, Ay, this);
                    if (Rownumber1 > 2) {
                        g.drawImage(new Alien().getImage(), Ax, Ay+30, this);
                        if (Rownumber1 == 3) {
                            g.drawImage(new Alien().getImage(), Ax, Ay+60, this);
                        }
                    }
                }
                if (Ax + 30 < ShotX && Ax + 25 > ShotX) {
                    if (Ay + SpaceY1 > ShotY) {
                        score = score + 1;
                        SpaceY1 = SpaceY1 - 30;
                        Rownumber1 = Rownumber1 - 1;
                        ShotX=0;
                        ShotY =0;
                        if (SpaceY1 == 0) {
                            row1 = false;
                        }
                    }
                }
            }
            if (row2) {
                if (Rownumber2 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+30, Ay, this);
                    if (Rownumber2 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+30, Ay+30, this);
                        if (Rownumber2 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+30, Ay+60, this);                        }
                    }
                }
                if (Ax + 30 < ShotX && Ax + 30 + 25 > ShotX) {
                    if (Ay + SpaceY2 > ShotY) {
                        score = score + 1;
                        SpaceY2 = SpaceY2 - 30;
                        Rownumber2 = Rownumber2 - 1;
                        if (SpaceY2 == 0) {
                            row2 = false;
                        }
                    }
                }
            }
            if (row3) {
                if (Rownumber3 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+60, Ay, this);
                    if (Rownumber3 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+60, Ay+30, this);
                        if (Rownumber3 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+60, Ay+60, this);
                        }
                    }
                }
                if (Ax + 60 < ShotX && Ax + 60 + 25 > ShotX) {
                    if (Ay + SpaceY3 > ShotY) {
                        score = score + 1;
                        SpaceY3 = SpaceY3 - 30;
                        Rownumber3 = Rownumber3 - 1;
                        if (SpaceY3 == 0) {
                            row3 = false;
                        }
                    }
                }
            }
            if (row4) {
                if (Rownumber4 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+90, Ay, this);
                    if (Rownumber4 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+90, Ay+30, this);
                        if (Rownumber4 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+90, Ay+60, this);
                        }
                    }
                }
                if (Ax + 90 < ShotX && Ax + 90 + 25 > ShotX) {
                    if (Ay + SpaceY4 > ShotY) {
                        score = score + 1;
                        SpaceY4 = SpaceY4 - 30;
                        Rownumber4 = Rownumber4 - 1;
                        if (SpaceY4 == 0) {
                            row4 = false;
                        }
                    }
                }
            }
            if (row5) {
                if (Rownumber5 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+120, Ay, this);
                    if (Rownumber5 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+120, Ay + 30, this);
                        if (Rownumber5 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+120, Ay + 60, this);
                        }
                    }
                }
                if (Ax + 120 < ShotX && Ax + 120 + 25 > ShotX) {
                    if (Ay + SpaceY5 > ShotY) {
                        score = score + 1;
                        SpaceY5 = SpaceY5 - 30;
                        Rownumber5 = Rownumber5 - 1;
                        if (SpaceY5 == 0) {
                            row5 = false;
                        }
                    }
                }
            }
            if (row6) {
                if (Rownumber6 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+150, Ay, this);
                    if (Rownumber6 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+150, Ay+30, this);
                        if (Rownumber6 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+150, Ay+60, this);
                        }
                    }
                }
                if (Ax + 150 < ShotX && Ax + 150 + 25 > ShotX) {
                    if (Ay + SpaceY6 > ShotY) {
                        score = score + 1;
                        SpaceY6 = SpaceY6 - 30;
                        Rownumber6 = Rownumber6 - 1;
                        if (SpaceY6 == 0) {
                            row6 = false;
                        }
                    }
                }
            }
            if (row7) {
                if (Rownumber7 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+180, Ay, this);
                    if (Rownumber7 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+180, Ay+30, this);
                        if (Rownumber7 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+180, Ay+60, this);
                        }
                    }
                }
                if (Ax + 180 < ShotX && Ax + 180 + 25 > ShotX) {
                    if (Ay + SpaceY7 > ShotY) {
                        score = score + 1;
                        SpaceY7 = SpaceY7 - 30;
                        Rownumber7 = Rownumber7 - 1;
                        if (SpaceY7 == 0) {
                            row7= false;
                        }
                    }
                }
            }
            if (row8) {
                if (Rownumber8 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+210, Ay, this);
                    if (Rownumber8 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+210, Ay+30, this);
                        if (Rownumber8 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+210, Ay+60, this);
                        }
                    }
                }
                if (Ax + 210 < ShotX && Ax + 210 + 25 > ShotX) {
                    if (Ay + SpaceY8 > ShotY) {
                        score = score + 1;
                        SpaceY8 = SpaceY8 - 30;
                        Rownumber8 = Rownumber8 - 1;
                        if (SpaceY8 == 0) {
                            row8 = false;
                        }
                    }
                }
            }
            if (row9) {
                if (Rownumber9 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+240, Ay, this);
                    if (Rownumber9 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+240, Ay+30, this);
                        if (Rownumber9 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+240, Ay+60, this);
                        }
                    }
                }
                if (Ax + 240 < ShotX && Ax + 240 + 25 > ShotX) {
                    if (Ay + SpaceY9 > ShotY) {
                        score = score + 1;
                        SpaceY9 = SpaceY9 - 30;
                        Rownumber9 = Rownumber9 - 1;
                        if (SpaceY9 == 0) {
                            row9 = false;
                        }
                    }
                }
            }
            if (row10) {
                if (Rownumber10 > 1) {
                    g.drawImage(new Alien().getImage(), Ax+270, Ay, this);
                    if (Rownumber10 > 2) {
                        g.drawImage(new Alien().getImage(), Ax+270, Ay+30, this);
                        if (Rownumber10 == 3) {
                            g.drawImage(new Alien().getImage(), Ax+270, Ay+60, this);
                        }
                    }
                }
                if (Ax + 270 < ShotX && Ax + 270 + 25 > ShotX) {
                    if (Ay + SpaceY10 > ShotY) {
                        score = score + 1;
                        SpaceY10 = SpaceY10 - 30;
                        Rownumber10 = Rownumber10 - 1;
                        if (SpaceY10 == 0) {
                            row10 = false;
                        }
                    }
                }
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
    }

        public void actionPerformed(ActionEvent e){
            repaint();
        }

        private class TAdapter extends KeyAdapter {
            public void keyReleased(KeyEvent e){
                int key = e.getKeyCode();

                if(key == 37 || key ==39){
                    shipSpeed = 0;
                }
            }

            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if(key==37){
                    shipSpeed = -50;
                } else if (key == 39) {
                    shipSpeed = 50;
                } else if (key == 32) {
                    ship.getBulletList().add(new Bullet(ship.getX()+25));
                }
            }
        }



    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 500;
        long time =
                System.currentTimeMillis();
        while (true) {//infinite loop
            // spriteManager.update();
            repaint();
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0,time -
                        System.currentTimeMillis()));
            }catch (InterruptedException e) {
                System.out.println(e);
            }//end catch
        }//end while loop




    }//end of run

}//end of class

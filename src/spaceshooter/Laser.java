package spaceshooter;

import java.awt.*;

public class Laser {
    private int x, y;

    public Laser(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= 10;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 5, 15);
    }

    public int getY() { return y; }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 15);
    }
}
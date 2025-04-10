package spaceshooter;

import java.awt.*;

public class Enemy {
    private int x, y;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y += 3;
    }

    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, 40, 30);
    }

    public int getY() { return y; }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 30);
    }
}
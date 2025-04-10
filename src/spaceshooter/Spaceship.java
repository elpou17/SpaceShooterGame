package spaceshooter;

import java.awt.*;

public class Spaceship {
    private int x, y;
    private int shootCooldown = 0;

    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx) {
        x += dx;
        if (x < 0) x = 0;
        if (x > 750) x = 750;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 50, 30);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean canShoot() {
        return shootCooldown <= 0;
    }

    public void resetShootTimer() {
        shootCooldown = 15;
    }

    public void updateShootTimer() {
        if (shootCooldown > 0) shootCooldown--;
    }
}
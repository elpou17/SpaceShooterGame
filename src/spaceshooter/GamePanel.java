package spaceshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Spaceship player;
    private ArrayList<Laser> lasers;
    private ArrayList<Enemy> enemies;
    private boolean left, right, shooting;
    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        player = new Spaceship(375, 500);
        lasers = new ArrayList<>();
        enemies = new ArrayList<>();
        timer = new Timer(16, this);
    }

    public void startGame() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (left) player.move(-5);
        if (right) player.move(5);
        if (shooting && player.canShoot()) {
            lasers.add(new Laser(player.getX() + 20, player.getY()));
            player.resetShootTimer();
        }

        player.updateShootTimer();

        for (Laser laser : lasers) {
            laser.move();
        }

        spawnEnemies();
        for (Enemy enemy : enemies) {
            enemy.move();
        }

        checkCollisions();

        repaint();
    }

    private void spawnEnemies() {
        if (new Random().nextInt(50) == 0) {
            enemies.add(new Enemy(new Random().nextInt(750), 0));
        }
    }

    private void checkCollisions() {
        Iterator<Laser> laserIt = lasers.iterator();
        while (laserIt.hasNext()) {
            Laser laser = laserIt.next();
            Iterator<Enemy> enemyIt = enemies.iterator();
            while (enemyIt.hasNext()) {
                Enemy enemy = enemyIt.next();
                if (laser.getBounds().intersects(enemy.getBounds())) {
                    laserIt.remove();
                    enemyIt.remove();
                    score += 10;
                    break;
                }
            }
        }

        // Eliminar proyectiles fuera de la pantalla
        lasers.removeIf(l -> l.getY() < 0);
        enemies.removeIf(e -> e.getY() > 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (Laser laser : lasers) laser.draw(g);
        for (Enemy enemy : enemies) enemy.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Puntuación: " + score, 10, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) shooting = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) shooting = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
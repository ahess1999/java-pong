package com.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Paddle extends KeyAdapter implements Runnable {

    /**
     * Differentiates between the arrow keys or w and s keys
     */
    private int id;

    private int x;
    private int y;
    private int yDir;

    public Rectangle paddle;

    public Paddle(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        paddle = new Rectangle(x, y, 10, 40);
    }

    public void keyPressed(KeyEvent e) {
        if(id == 1) {
            if(e.getKeyCode() == KeyEvent.VK_W) {
                yDir = -1;
            }
            if(e.getKeyCode() == KeyEvent.VK_S) {
                yDir = 1;
            }
        }
        if(id == 2) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                yDir = -1;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                yDir = 1;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if(id == 1) {
            if(e.getKeyCode() == KeyEvent.VK_W) {
                yDir = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_S) {
                yDir = 0;
            }
        }
        if(id == 2) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                yDir = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                yDir = 0;
            }
        }
    }

    private void move() {
        paddle.y += 2 * yDir;
        if(paddle.y >= 560) {
            paddle.y = 560;
        }
        if(paddle.y <= 40) {
            paddle.y = 40;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    @Override
    public void run() {
        try {
            while(true) {
                move();
                Thread.sleep(5);
            }
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

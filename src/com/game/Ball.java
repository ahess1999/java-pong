package com.game;

import java.awt.*;
import java.util.Random;

import static com.game.Pong.player1;
import static com.game.Pong.player2;

public class Ball implements Runnable {

    private int x;
    private int y;
    private int xDir;
    private int yDir;

    private Rectangle ball;

    public int player1Score = 0;
    public int player2Score = 0;


    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.xDir = getRandomDirection();
        this.yDir = getRandomDirection();
        ball = new Rectangle(x, y, 20, 20);
    }

    private int getRandomDirection() {
        Random r = new Random();
        if(r.nextInt(2) == 0) {
            return -1;
        }
        else {
            return 1;
        }
    }

    private void checkCollisionWithPaddles() {
        if(ball.intersects(player1.paddle)) {
            xDir = 1;
        }
        if(ball.intersects(player2.paddle)) {
            xDir = -1;
        }

    }

    private void resetBall() {
        ball.x = this.x;
        ball.y = this.y;
        xDir = getRandomDirection();
        yDir = getRandomDirection();
    }

    private void checkCollisionWithEdges() {
        if(ball.x >= 780) {
            player1Score++;
            resetBall();
        }
        if(ball.x <= 20) {
            player2Score++;
            resetBall();
        }
        if(ball.y >= 580) {
            this.yDir = -1;
        }
        if(ball.y <= 20) {
            this.yDir = 1;
        }
    }

    private void move() {
        checkCollisionWithPaddles();
        ball.x += xDir;
        ball.y += yDir;
        checkCollisionWithEdges();
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(ball.x, ball.y, ball.width, ball.height);
    }

    public void resetGame() {
        resetBall();
        player1Score = 0;
        player2Score = 0;
    }

    @Override
    public void run() {
        try {
            while(true) {
                move();
                Thread.sleep(4);
            }
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

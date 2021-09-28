package com.game;

import javax.swing.*;
import java.awt.*;

public class Pong extends JFrame {

    Image image;
    Graphics graphics;

    public static Ball ball = new Ball(400, 300);
    public static Paddle player1 = new Paddle(1, 30, 300);
    public static Paddle player2 = new Paddle(2, 760, 300);

    public Pong() {
        this.setTitle("Pong!");
        this.setSize(800, 600);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setBackground(Color.darkGray);
        this.addKeyListener(new KeyPress());
    }

    @Override
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    private void draw(Graphics g) {
        if(!checkGameOver(g)) {
            ball.draw(g);
            player1.draw(g);
            player2.draw(g);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString(String.valueOf(ball.player1Score), 175, 60);
            g.drawString(String.valueOf(ball.player2Score), 575, 60);
            repaint();
        }
    }

    private boolean checkGameOver(Graphics g) {
        if(ball.player1Score == 5) {
            gameOverDisplay(g, "Player 1");
            return true;
        }
        else if(ball.player2Score == 5) {
            gameOverDisplay(g, "Player 2");
            return true;
        }
        return false;
    }

    private void gameOverDisplay(Graphics g, String player) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.drawString(player + " Won!", 70, 300);
        JButton playAgain = new JButton("Play Again");
        playAgain.setBounds(300, 400, 200, 50);
        playAgain.addActionListener(e -> gameOver());
        playAgain.setVisible(true);
        this.add(playAgain);
        playAgain.requestFocusInWindow();
    }

    private void gameOver() {
        ball.resetGame();
        repaint();
    }

    public static void main(String[] args) {
        Pong pg = new Pong();

        Thread b = new Thread(ball);
        b.start();
        Thread p1 = new Thread(player1);
        Thread p2 = new Thread(player2);
        p1.start();
        p2.start();
    }
}

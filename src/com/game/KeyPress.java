package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.game.Pong.player1;
import static com.game.Pong.player2;

public class KeyPress extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        player1.keyPressed(e);
        player2.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        player1.keyReleased(e);
        player2.keyReleased(e);
    }
}

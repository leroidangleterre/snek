/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snek;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author arthu
 */
public class SnekKeyListener implements KeyListener {

    private GraphicPanel p;

    public SnekKeyListener(GraphicPanel newPanel) {
        p = newPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_P:
            p.playPause();
            break;
        case KeyEvent.VK_S:
            p.step();
            break;
        case KeyEvent.VK_LEFT:
            p.turnSnekLeft();
            break;
        case KeyEvent.VK_RIGHT:
            p.turnSnekRight();
            break;
        default:
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {

    GamePanel gp;
    public boolean keyUp, keyDown, keyLeft, keyRight, keyEnter;
    boolean checkDrawTime = false;

    public KeyInputs(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); //returns the integer keycode associated with the key in this event

        if(gp.GameState == gp.PlayState) {
            if (code == KeyEvent.VK_W) {
                keyUp = true;
            }
            if (code == KeyEvent.VK_S) {
                keyDown = true;
            }
            if (code == KeyEvent.VK_A) {
                keyLeft = true;
            }
            if (code == KeyEvent.VK_D) {
                keyRight = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.GameState = gp.PauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                keyEnter = true;
            }

            //debug
            if (code == KeyEvent.VK_T) {
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                } else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
        }

        // pause state
        else if (gp.GameState == gp.PauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.GameState = gp.PlayState;
            }
        }

        // dialogue state
        else if (gp.GameState == gp.DialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.GameState = gp.PlayState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            keyUp = false;
        }
        if (code == KeyEvent.VK_S) {
            keyDown = false;
        }
        if (code == KeyEvent.VK_A) {
            keyLeft = false;
        }
        if (code == KeyEvent.VK_D) {
            keyRight = false;
        }
    }
}

package com.asterfox.game;

import com.asterfox.game.entities.Asteroid;
import com.asterfox.game.entities.Bullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Input implements InputProcessor {

    private final GameScreen gs;

    public Input(GameScreen gs){
        this.gs = gs;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(
            int screenX,
            int screenY,
            int pointer,
            int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        gs.cam.unproject(touchPos);

        if (gs.uiHandler.leftbutton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.leftbutton.button.setScale(1.2f,1.2f);
            gs.player.moveLeft();
            gs.soundHandler.playEngine();
        }
        if (gs.uiHandler.rightButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.rightButton.button.setScale(1.2f, 1.2f);
            gs.player.moveRight();
            gs.soundHandler.playEngine();
        }
        if (gs.uiHandler.fireButton.checkIfClicked(touchPos.x, touchPos.y)){
            for (int i = 0; i < gs.asteroids.asteroids.size; i++) {
                if(gs.asteroids.asteroids.get(i).isDestroyable(1)){
                    gs.asteroids.asteroids.removeIndex(i);
                }
            }
        }
        if (gs.uiHandler.up.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.cursor.move(0);
        }
        if (gs.uiHandler.down.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.cursor.move(2);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gs.uiHandler.leftbutton.button.setScale(1,1);
        gs.uiHandler.rightButton.button.setScale(1, 1);
        gs.uiHandler.fireButton.button.setScale(1, 1);
        gs.player.resetPlayerMovement();
        gs.player.isMoving = false;
        gs.soundHandler.engine.stop();
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

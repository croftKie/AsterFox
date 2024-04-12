package com.asterfox.game;

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
            gs.uiHandler.leftbutton.button.setScale(1.5f,1.5f);
            gs.player.moveLeft();
            gs.soundHandler.playEngine();
        }
        if (gs.uiHandler.rightButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.rightButton.button.setScale(1.5f, 1.5f);
            gs.player.moveRight();
            gs.soundHandler.playEngine();
        }
        if (gs.uiHandler.fireButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.fireButton.button.setScale(1.5f, 1.5f);
            boolean spawned = gs.bullets.spawnBullet();
            if (spawned){
                gs.soundHandler.playlaser();
            }
        }
        if (gs.uiHandler.speedButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.speedButton.button.setScale(1.5f, 1.5f);
            gs.player.speed = 7;
        }
        if (gs.uiHandler.reloadButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.uiHandler.reloadButton.button.setScale(1.5f, 1.5f);
            gs.bullets.bulletsLoaded = 6;
            gs.uiHandler.generateHUD();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gs.uiHandler.leftbutton.button.setScale(1,1);
        gs.uiHandler.rightButton.button.setScale(1, 1);
        gs.uiHandler.fireButton.button.setScale(1, 1);
        gs.uiHandler.speedButton.button.setScale(1, 1);
        gs.uiHandler.reloadButton.button.setScale(1, 1);
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

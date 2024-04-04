package com.asterfox.game;

import com.asterfox.game.entities.Bullets;
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

        if (gs.leftbutton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.player.moveLeft();
        }
        if (gs.rightButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.player.moveRight();
        }
        if (gs.fireButton.checkIfClicked(touchPos.x, touchPos.y)){
            if (TimeUtils.nanoTime() - gs.lastBulletSpawned > 250000000){
                gs.bullets.spawnBullet();
            }

        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gs.player.resetPlayerMovement();
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

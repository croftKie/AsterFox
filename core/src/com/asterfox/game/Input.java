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

        if (gs.leftbutton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.leftbutton.button.setScale(1.5f,1.5f);
            gs.player.moveLeft();
            gs.soundHandler.playEngine();
        }
        if (gs.rightButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.rightButton.button.setScale(1.5f, 1.5f);
            gs.player.moveRight();
            gs.soundHandler.playEngine();
        }
        if (gs.fireButton.checkIfClicked(touchPos.x, touchPos.y)){
            gs.fireButton.button.setScale(1.5f, 1.5f);
            gs.bullets.spawnBullet();
            gs.soundHandler.playlaser();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gs.leftbutton.button.setScale(1,1);
        gs.rightButton.button.setScale(1, 1);
        gs.fireButton.button.setScale(1, 1);

        gs.player.resetPlayerMovement();
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

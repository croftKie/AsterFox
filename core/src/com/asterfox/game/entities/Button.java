package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Button extends Entity {
    public Sprite button;

    public Button(String assetFile, float[] dimens){
        button = createAsset(assetFile, dimens);
    }

    public void draw(AsterFox game){
        draw(game, button);
    }

    public boolean checkIfClicked (float ix, float iy) {
        if (ix > button.getX() && ix < button.getX() + button.getWidth()) {
            if (iy > button.getY() && iy < button.getY() + button.getHeight()) {
                return true;
            }
        }
        return false;
    }
}

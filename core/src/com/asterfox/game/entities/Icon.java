package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Icon extends Entity{
    //    private Sprite skin;
    public Sprite icon;

    public Icon(String assetFile, float[] dimens){
        icon = createAsset(assetFile, dimens);
    }

    public void draw(AsterFox game){
        draw(game, icon);
    }
}

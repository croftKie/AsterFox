package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Asteroid extends Entity{
    public Sprite asteroid;
    public int[] rotationOptions = new int[]{2, 4, -1, -3};
    public int rotation;

    public Asteroid(String assetFile, float[] dimens){
        asteroid = createAsset(assetFile, dimens);
        this.rotation = rotationOptions[MathUtils.random(0, 3)];
    }

    public void draw(AsterFox game){
        draw(game, asteroid);
    }

    public void moveAsteroid(){
        float y = asteroid.getY() - 3;
        asteroid.setY(y);
        asteroid.rotate(rotation);
    }

    public boolean isDestroyable(Array<Bullets> bullets){
        if(asteroid.getY() < 0){
            return true;
        }
        return false;
    }
}

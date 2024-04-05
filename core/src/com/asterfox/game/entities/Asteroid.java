package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Asteroid extends Entity{
    public GameScreen gs;
    public Sprite asteroid;
    public int[] rotationOptions = new int[]{2, 4, -1, -3};
    public float[] xOptions = new float[]{2, -1, 0, 0.5f, -0.2f, 1.3f};
    public int rotation;
    public float xOption;

    public Asteroid(String assetFile, float[] dimens, GameScreen gs){
        this.gs = gs;
        asteroid = createAsset(assetFile, dimens);
        this.rotation = rotationOptions[MathUtils.random(0, 3)];
        this.xOption = xOptions[MathUtils.random(0,5)];
    }


    public void draw(AsterFox game){
        draw(game, asteroid);
    }

    public void moveAsteroid(){
        float y = asteroid.getY() - (3 * (gs.waveHandler.wave * 0.55f));
        asteroid.setY(y);
        asteroid.setX(asteroid.getX() + xOption);
        asteroid.rotate(rotation);
    }

    public boolean isDestroyable(Array<Bullets> bullets){
        if(asteroid.getY() < 0){
            return true;
        }
        return false;
    }
}

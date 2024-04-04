package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Bullets extends Entity{
    public Sprite bullet;
    GameScreen gs;
    public Bullets(String assetFile, float[] dimens, GameScreen gs){
        bullet = createAsset(assetFile, dimens);
        this.gs = gs;
    }

    public void draw(AsterFox game){
        draw(game, bullet);
    }

    public void moveBullet(){
        float y = bullet.getY() + 5;
        bullet.setY(y);
    }

    public boolean isDestroyable(Array<Asteroid> asteroids){
        if(bullet.getY() > 480){
            return true;
        }

        Iterator<Asteroid> iter = asteroids.iterator();
        while (iter.hasNext()){
            if (bullet.getBoundingRectangle().overlaps(iter.next().asteroid.getBoundingRectangle())){
                iter.remove();
                gs.score--;
                return true;
            }
        }
        return false;
    }
}

package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.managers.AsteroidHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Player extends Entity{
    private OrthographicCamera cam;
    public Sprite player;
    public Texture EngineEffect;
    public Rectangle playerBounds = new Rectangle();
    public Rectangle engineEffectBounds = new Rectangle();
    public boolean destroyed = false;
    public Player(String assetFile, float[] dimens, OrthographicCamera cam){
        this.cam = cam;
        player = createAsset(assetFile, dimens);
        EngineEffect = createAsset(
                "engine_effect.png",
                engineEffectBounds,
                new float[] {
                        playerBounds.x,
                        playerBounds.y - 40,
                        playerBounds.height,
                        playerBounds.width,
                    }
                    );
    }

    public void draw(AsterFox game){
        draw(game, player);
        draw(game, EngineEffect, engineEffectBounds);
    }

    public void moveLeft(){
        float x = player.getX() - 5;

        if (x > 0){
            player.setX(x);
            player.setRotation(10);
            engineEffectBounds.x -= 10;
        }
    }
    public void moveRight(){
        float x = player.getX() + 5;
        if (x < 800 - 64){
            player.setX(x);
            player.setRotation(-10);
            engineEffectBounds.x += 10;
        }
    }
    public void resetPlayerMovement(){
        player.setRotation(0);
    }


    public void collidedWithAsteroid(AsteroidHandler asteroids){
        Iterator<Asteroid> AsterIt = asteroids.asteroids.iterator();
        while (AsterIt.hasNext()){
            Asteroid currentAsteroid = AsterIt.next();
            currentAsteroid.moveAsteroid();
            if (currentAsteroid.hasHitPlayer(player)){
                currentAsteroid.asteroid.setX(-currentAsteroid.asteroid.getX());
                destroyed = true;
            }
        }
    }
}

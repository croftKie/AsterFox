package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameOver;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MainMenu;
import com.asterfox.game.managers.AsteroidHandler;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

public class Player extends Entity{
    private OrthographicCamera cam;
    public Sprite player;
    public Texture EngineEffect;
    public Rectangle playerBounds = new Rectangle();
    public Rectangle engineEffectBounds = new Rectangle();
    public GameScreen gs;
    public MainMenu mm;
    public boolean destroyed = false;
    public Player(GameScreen gs, String assetFile, float[] dimens, OrthographicCamera cam){
        this.gs = (GameScreen) gs;
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
    public Player(MainMenu gs, String assetFile, float[] dimens, OrthographicCamera cam){
        this.mm = gs;
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


    public void destroyPlayer(AsteroidHandler asteroids){
        Iterator<Asteroid> AsterIt = asteroids.asteroids.iterator();
        while (AsterIt.hasNext()){
            Asteroid currentAsteroid = AsterIt.next();
            if (currentAsteroid.asteroid.getBoundingRectangle().overlaps(player.getBoundingRectangle())){
                System.out.println("firing!!!");
                currentAsteroid.asteroid.setX(currentAsteroid.asteroid.getX() + -4);

                destroyed =  true;
                break;
            }
        }
    }
}

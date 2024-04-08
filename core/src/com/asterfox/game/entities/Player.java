package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameOver;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MainMenu;
import com.asterfox.game.animations.Engine;
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
    public GameScreen gs;
    public MainMenu mm;
    public boolean destroyed = false;
    public int speed = 5;
    public Player(GameScreen gs, String assetFile, float[] dimens, OrthographicCamera cam){
        this.gs = (GameScreen) gs;
        this.cam = cam;
        player = createAsset(assetFile, dimens);

        gs.aniHandler.createEngineAnim(
                player.getX(),
                player.getY() - player.getHeight());
    }

//    PLAYER CONSTRUCTOR FOR MAIN MENU
    public Player(MainMenu gs, String assetFile, float[] dimens, OrthographicCamera cam){
        this.mm = gs;
        this.cam = cam;
        player = createAsset(assetFile, dimens);
    }

    public void draw(AsterFox game){
        draw(game, player);
    }

    public void moveLeft(){
        float x = player.getX() - speed;

        if (x > 0){
            player.setX(x);
            player.setRotation(10);
        }
    }
    public void moveRight(){
        float x = player.getX() + speed;
        if (x < 800 - 64){
            player.setX(x);
            player.setRotation(-10);
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
                currentAsteroid.asteroid.setX(-currentAsteroid.asteroid.getX());
                gs.aniHandler.engine.complete = true;
                gs.soundHandler.playExplosion();
                gs.aniHandler.createExplosionAnim(
                        player.getX() - (player.getWidth() / 2),
                        player.getY() + (player.getHeight() / 2)
                );
                destroyed =  true;
                break;
            }
        }
    }


}

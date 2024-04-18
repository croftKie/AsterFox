package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameOver;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MainMenu;
import com.asterfox.game.animations.Engine;
import com.asterfox.game.managers.AsteroidHandler;
import com.badlogic.gdx.Gdx;
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
    public float speed = 5;
    public float acceleration = 6f;
    public boolean moveLeft, moveRight, isMoving;

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
    public void update(){
         if(speed > 0 && !isMoving) {
             speed -= acceleration * gs.deltaTime;
             if (moveLeft && player.getX() > 0){
                player.setX(player.getX() - speed);
             }
             if (moveRight && player.getX() < 800 - 64){
                 player.setX(player.getX() + speed);
             }
         } else if (speed <= 0) {
             moveRight = false;
             moveLeft = false;
         }
    }

    public void moveLeft(){
        moveLeft = true;
        isMoving = true;
        speed = 5;

        float x = player.getX() - speed;

        if (x >= 0){
            player.setX(x);
            player.setRotation(10);
        }
    }
    public void moveRight(){
        moveRight = true;
        isMoving = true;
        speed = 5;
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
            if (
                    currentAsteroid.asteroid.getBoundingRectangle().contains(
                            player.getX() + player.getWidth() / 2,
                            player.getY() + player.getHeight() / 2
                    )
            ){
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

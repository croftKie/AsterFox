package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.entities.Asteroid;
import com.asterfox.game.entities.Bullets;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class AsteroidHandler {
    GameScreen gs;
    Asteroid asteroid;
    public String[] asteroidAssets = {"meteor_small.png", "meteor_large.png", "meteor_square_small.png", "meteor_square_large.png"};
    public Array<Asteroid> asteroids;
    public long lastAsteroidSpawned;
    public AsteroidHandler(GameScreen gs){
        this.gs = gs;
        asteroids = new Array<Asteroid>();
        lastAsteroidSpawned = TimeUtils.nanoTime();
    }

    public void spawnAsteroid(){
        if (TimeUtils.nanoTime() - lastAsteroidSpawned > 1000000000){
            generateAsteroid();
            lastAsteroidSpawned = TimeUtils.nanoTime();
        }
    }

    public void generateAsteroid(){
        asteroid = new Asteroid(
                asteroidAssets[MathUtils.random(0, 3)],
                new float[]{
                        MathUtils.random(0, 800 - 64),
                        500,
                        64,
                        64
                },
                gs
        );
        asteroids.add(asteroid);
    }

    public void moveDestroyAsteroids(Array<Bullets> bullets){
        Iterator<Asteroid> AsterIt = asteroids.iterator();
        while (AsterIt.hasNext()){
            Asteroid currentAsteroid = AsterIt.next();
            currentAsteroid.moveAsteroid();
        }
    }
}

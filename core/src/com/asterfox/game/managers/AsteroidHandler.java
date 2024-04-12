package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.entities.Asteroid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class AsteroidHandler {
    public String[] asteroidAssets = {
            "meteor_small.png",
            "meteor_large.png",
            "meteor_square_small.png",
            "meteor_square_large.png"
    };
    public int[] rotationOptions = new int[]{2, 4, -1, -3};
    public float[] xOptions = new float[]{2, -1, 0, 0.5f, -0.2f, 1.3f};
    public Array<Asteroid> asteroids;
    private final GameScreen gs;
    public long lastAsteroidSpawned;

    public AsteroidHandler(GameScreen gs){
        this.gs = gs;
        asteroids = new Array<Asteroid>(){};
    }

    public void render(){
        for (Asteroid asteroid : asteroids) {
            asteroid.render(gs.game);
        }
    }
    public void update(){
        for (int i = 0; i < asteroids.size; i++) {
            if (asteroids.get(i).isDestroyable()){
                asteroids.removeIndex(i);
            }
            asteroids.get(i).update();
        }
    }

    public void spawnAsteroid(){
        if (TimeUtils.nanoTime() - lastAsteroidSpawned > 1000000000 / (gs.game.waveHandler.wave * 0.55f)){
            Asteroid asteroid = new Asteroid(
                    asteroidAssets[MathUtils.random(0,3)],
                    new float[]{
                    MathUtils.random(0, 800),
                    480 + 64,
                    64,
                    64
                    },
                    gs,
                    rotationOptions[MathUtils.random(0,3)],
                    xOptions[MathUtils.random(0,3)]);
            asteroids.add(asteroid);
            lastAsteroidSpawned = TimeUtils.nanoTime();
        }
    };

}

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
            "meteor_large.png",
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
            if (asteroids.get(i).isDestroyable(0)){
                asteroids.removeIndex(i);
            }
            if (asteroids.size > 0){
                asteroids.get(i).update();
            }
        }
    }

    public void spawnAsteroid(){
        if (TimeUtils.nanoTime() - lastAsteroidSpawned > 2000000000 / (gs.game.waveHandler.wave * 0.55f)){
            Asteroid asteroid = new Asteroid(
                    asteroidAssets[MathUtils.random(0,1)],
                    new float[]{
                    MathUtils.random(gs.player.player.getX() - 300, gs.player.player.getX() + 300),
                    MathUtils.random(gs.player.player.getY() + 300, gs.player.player.getY() + 800),
                    0,
                    0
                    },
                    gs,
                    rotationOptions[MathUtils.random(0,3)],
                    xOptions[MathUtils.random(0,3)]);
            asteroids.add(asteroid);
            lastAsteroidSpawned = TimeUtils.nanoTime();
        }
    };

}

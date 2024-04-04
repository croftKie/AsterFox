package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.entities.Asteroid;
import com.asterfox.game.entities.Bullets;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class BulletHandler {
    public GameScreen gs;
    Bullets bullet;
    public Array<Bullets> bullets;
    public long lastBulletSpawned;
    public BulletHandler(GameScreen gs){
        this.gs = gs;
        bullets = new Array<Bullets>();
        lastBulletSpawned = TimeUtils.nanoTime();
    }

    public void spawnBullet(){
        if (TimeUtils.nanoTime() - lastBulletSpawned > 250000000){
            generateBullet();
            lastBulletSpawned = TimeUtils.nanoTime();
        }
    }

    public void generateBullet(){
        Bullets b = new Bullets(
                "bullet.png",
                new float[]{
                        gs.player.player.getX(),
                        gs.player.player.getY() + 64,
                        64,
                        64
                }, gs);
        bullets.add(b);
    }

    public void moveDestroyBullets(AsteroidHandler asteroids){
        Iterator<Bullets> iter = bullets.iterator();
        while (iter.hasNext()){
            Bullets currentBullet = iter.next();
            currentBullet.moveBullet();
            if (currentBullet.isDestroyable(asteroids.asteroids)){
                iter.remove();
            }
        }
    }
}


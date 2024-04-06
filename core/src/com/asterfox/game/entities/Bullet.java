package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Bullet extends Entity{
    private final float[] dimens;
    public Sprite bullet;
    public Array<Sprite> bullets;
    public long lastBulletSpawned;
    private final GameScreen gs;
    private String asset;

    public Bullet(String assetFile, float[] dimens, GameScreen gs){
        bullet = createAsset(assetFile, dimens);
        bullets = new Array<Sprite>();
        this.gs = gs;
        this.asset = assetFile;
        this.dimens = dimens;
    }

    public void render(AsterFox game){
        draw(game, bullet);
    }

    public void update(){
        moveBullets();
    }

    public void spawnBullet(){
        if (TimeUtils.nanoTime() - lastBulletSpawned > 250000000){
            bullet = createAsset(asset, dimens);
            bullets.add(bullet);
            lastBulletSpawned = TimeUtils.nanoTime();
        }
    }

    private void moveBullets(){
        float y = bullet.getY() + 5;
        bullet.setY(y);

    }

    public boolean isDestroyable(){
        if(bullet.getBoundingRectangle().getY() > 480 + 64){
            return true;
        }

        for (int i = 0; i < gs.asteroids.asteroids.size; i++) {
            if (bullet.getBoundingRectangle().overlaps(gs.asteroids.asteroids.get(i).asteroid.getBoundingRectangle())) {
                gs.hitAsteroid = gs.asteroids.asteroids.get(i).asteroid;

                gs.asteroids.asteroids.removeIndex(i);
                gs.waveHandler.decreaseScore();
                gs.soundHandler.playExplosion();
                return true;
            }
        }
        return false;
    }
}

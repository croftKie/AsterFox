package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.entities.Asteroid;
import com.asterfox.game.entities.Bullet;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class BulletHandler {
    public Array<Bullet> bullets;
    private final GameScreen gs;
    public long lastBulletSpawned;

    public BulletHandler(GameScreen gs){
        this.gs = gs;
        bullets = new Array<Bullet>(){};
    }

    public void render(){
        for (Bullet bullet : bullets) {
            bullet.render(gs.game);
        }
    }
    public void update(){
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).update();
            if (bullets.get(i).isDestroyable()){

                gs.aniHandler.createExplosionAnim(
                        bullets.get(i).bullet.getX() - (bullets.get(i).bullet.getWidth() / 2),
                        bullets.get(i).bullet.getY() + (bullets.get(i).bullet.getHeight() / 2)
                );

                bullets.removeIndex(i);
            }
        }
    }

    public boolean spawnBullet(){
        if (TimeUtils.nanoTime() - lastBulletSpawned > 500000000){
            Bullet bullet = new Bullet(
                    "bullet.png",
                    new float[]{
                            gs.player.player.getX(),
                            gs.player.player.getY(),
                            64,
                            64
                    },
                    gs);
            bullets.add(bullet);
            lastBulletSpawned = TimeUtils.nanoTime();
            return true;
        }
        return false;
    };
}

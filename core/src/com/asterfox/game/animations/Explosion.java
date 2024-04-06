package com.asterfox.game.animations;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.asterfox.game.entities.Asteroid;
import com.asterfox.game.entities.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends Entity {
    GameScreen gs;
    TextureRegion[] animationFrames;
    public Animation<TextureRegion> animation;
    Texture ss;
    public float x,y;
    public float num = 0f;
    public boolean complete = false;
    public Explosion(GameScreen gs, float y, float x){
        this.gs = gs;
        ss = new Texture("explosion_ani.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(ss, 256, 256);
        animationFrames = new TextureRegion[5];
        this.x = x;
        this.y = y;

        for (int i = 0; i < 5; i++) {
            animationFrames[i] = tmpFrames[0][i];
        }

        animation = new Animation(1f / 25f, animationFrames);
    }


    public void render(AsterFox game, float delta){
        float key = num += delta;

        game.batch.draw(animation.getKeyFrame(key), x, y, 128, 128);

        if(animation.isAnimationFinished(key)){
            complete = true;
        }
    }
}

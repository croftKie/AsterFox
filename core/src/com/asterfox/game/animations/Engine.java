package com.asterfox.game.animations;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Engine {
    GameScreen gs;
    TextureRegion[] animationFrames;
    public Animation<TextureRegion> animation;
    Texture ss;
    public float x,y;
    public float num = 0f;
    public boolean complete = false;

    public Engine(GameScreen gs, float y, float x){
        this.gs = gs;
        ss = new Texture("engine.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(ss, 512, 512);
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

        game.batch.draw(animation.getKeyFrame(key, true), x, y, 64, 64);

    }

    public void update(float p_x, float p_y){
        this.x = p_x;
        this.y = p_y;
    }
}


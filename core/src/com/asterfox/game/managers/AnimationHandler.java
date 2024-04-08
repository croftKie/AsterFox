package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.animations.Engine;
import com.asterfox.game.animations.Explosion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationHandler {
    GameScreen gs;
    public Array<Explosion> exploAnimations = new Array<Explosion>();
    public Engine engine;
    public AnimationHandler(GameScreen gs){
        this.gs = gs;
    }

    public void render(){
        for (Explosion exploAnimation : exploAnimations) {
            exploAnimation.render(gs.game, Gdx.graphics.getDeltaTime());
        }

        if (!engine.complete){
            engine.render(gs.game, Gdx.graphics.getDeltaTime());
        }

    }
    public void update(){
        for (int i = 0; i < exploAnimations.size; i++) {
            if(exploAnimations.get(i).complete){
                exploAnimations.removeIndex(i);
            }
        }

        if (!engine.complete){
            engine.update(
                    gs.player.player.getX(),
                    gs.player.player.getY() - gs.player.player.getHeight());
        }
    }
    public void createExplosionAnim(float x, float y){
        Explosion explosion = new Explosion(
                gs,
                y,
                x);
        exploAnimations.add(explosion);
    }
    public void createEngineAnim(float x, float y){
        Engine e = new Engine(
                gs,
                y,
                x);
        engine = e;
    }

}

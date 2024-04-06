package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Entity {

    public Entity(){

    }

    public Texture createAsset(
            String assetFile,
            Rectangle bounds,
            float[] dimens
    ){
        Texture asset = new Texture(Gdx.files.internal(assetFile));
        bounds.x = dimens[0];
        bounds.y = dimens[1];
        bounds.width = dimens[2];
        bounds.height = dimens[3];

        return asset;
    }
    public Sprite createAsset(String assetFile, float[] dimens){
        Texture asset = new Texture(Gdx.files.internal(assetFile));
        return createSkin(asset, dimens);
    }

    private Sprite createSkin(Texture texture, float[] dimens){
        Sprite skin = new Sprite(texture); // your image
        skin.setPosition(dimens[0], dimens[1]);
        skin.setSize(dimens[2], dimens[3]);
        skin.setOrigin(dimens[2] / 2, dimens[3] / 2);
        return skin;
    }

    public void draw(AsterFox game, Texture asset, Rectangle bounds){
        game.batch.draw(
                asset,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
    }
    public void draw(AsterFox game, Sprite asset){
        asset.draw(game.batch);
    }
    public void draw(AsterFox game, float elapsedTime, Animation<TextureRegion> ani, float x, float y){
        game.batch.draw(ani.getKeyFrame(elapsedTime), x, y);
    }


}
